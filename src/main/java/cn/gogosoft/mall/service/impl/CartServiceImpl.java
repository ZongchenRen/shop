package cn.gogosoft.mall.service.impl;

import static cn.gogosoft.mall.enums.ResponseEnum.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

import cn.gogosoft.mall.dao.ProductMapper;
import cn.gogosoft.mall.enums.ProductStatusEnum;
import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.pojo.Cart;
import cn.gogosoft.mall.pojo.Product;
import cn.gogosoft.mall.service.ICartService;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-01 00:43
 * @description 购物车服务
 */
@Service
public class CartServiceImpl implements ICartService {
	private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private StringRedisTemplate redisTemplate;

	private Gson gson = new Gson();

	@Override
	public ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm) {
		// 定义数量，避免魔法值
		Integer quantity = 1;
		Product product = productMapper.selectByPrimaryKey(cartAddForm.getProductId());
		// 检查商品是否存在
		if (product == null) {
			return ResponseVo.error(PRODUCT_NOT_EXIST);
		}
		// 是否是正常的在售状态
		if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
			return ResponseVo.error(PRODUCT_OFF_SALE_DELETE);
		}
		// 检查库存
		if (product.getStock() <= 0) {
			return ResponseVo.error(PRODUCT_STACK_ERROR);
		}
		// 写入redis 用map结构
		// key :cart_uid
		HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
		// 先读取
		String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
		String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
		Cart cart;
		if (StringUtils.isEmpty(value)) {
			// 没有该商品，新增
			cart = new Cart(product.getId(), quantity, cartAddForm.getSelected());
		} else {
			// 有该商品，数量 +1
			cart = gson.fromJson(value, Cart.class);
			cart.setQuantity(cart.getQuantity() + quantity);
		}
		opsForHash.put(String.format(CART_REDIS_KEY_TEMPLATE, uid), String.valueOf(product.getId()),
				gson.toJson(cart));

		return ResponseVo.success();
	}
}
