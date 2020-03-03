package cn.gogosoft.mall.service.impl;

import static cn.gogosoft.mall.enums.ResponseEnum.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.gogosoft.mall.dao.OrderItemMapper;
import cn.gogosoft.mall.dao.OrderMapper;
import cn.gogosoft.mall.dao.ProductMapper;
import cn.gogosoft.mall.dao.ShippingMapper;
import cn.gogosoft.mall.enums.OrderStatusEnum;
import cn.gogosoft.mall.enums.PaymentTypeEnum;
import cn.gogosoft.mall.enums.ProductStatusEnum;
import cn.gogosoft.mall.pojo.*;
import cn.gogosoft.mall.service.IOrderService;
import cn.gogosoft.mall.vo.OrderItemVo;
import cn.gogosoft.mall.vo.OrderVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-02 20:19
 * @description 订单服务
 */
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private ShippingMapper shippingMapper;
	@Autowired
	private CartServiceImpl cartService;
	@Autowired
	private ProductMapper productMapper;

	@Transactional
	@Override
	public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {
		// 校验收货地址（总之要查出来的）
		Shipping shipping = shippingMapper.selectByUidAndShippingId(uid, shippingId);
		if (shipping == null) {
			return ResponseVo.error(SHIPPING_NOT_EXIST);
		}
		// 获取购物车，校验
		List<Cart> cartList = cartService.listForCart(uid).stream().filter(Cart::getProductSelected)
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(cartList)) {
			return ResponseVo.error(CART_SELECTED_IS_EMPTY);
		}
		// 获取cartList里面的products,
		Set<Integer> productIdSet = cartList.stream().map(Cart::getProductId)
				.collect(Collectors.toSet());
		List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
		Map<Integer, Product> map = productList.stream()
				.collect(Collectors.toMap(Product::getId, product -> product));
		List<OrderItem> orderItemList = new ArrayList<>();
		Long orderNo = generateOrderNo();
		for (Cart cart : cartList) {
			// 根据productId查询数据库
			Product product = map.get(cart.getProductId());
			// 是否有商品
			if (product == null) {
				return ResponseVo.error(PRODUCT_NOT_EXIST, "商品不存在. productId = " + cart.getProductId());
			}
			// 商品上下架状态
			if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())) {
				return ResponseVo.error(PRODUCT_OFF_SALE_DELETE, "商品不是在售状态. " + product.getName());
			}
			// 判断库存是否充足
			if (product.getStock() < cart.getQuantity()) {
				ResponseVo.error(PRODUCT_STACK_ERROR, "库存正确. " + product.getName());
			}

			OrderItem orderItem = builderOrderItem(uid, orderNo, cart.getQuantity(), product);
			orderItemList.add(orderItem);
			// 减库存
			product.setStock(product.getStock() - cart.getQuantity());
			int row = productMapper.updateByPrimaryKeySelective(product);
			if (row <= 0) {
				ResponseVo.error(ERROR);
			}
		}

		// 计算总价格，只计算被选中的商品
		// 生成订单，入库：order和orderItem，需要用事物，保证两个表的数据一致
		Order order = builderOrder(uid, orderNo, shippingId, orderItemList);
		int rowForOrder = orderMapper.insertSelective(order);
		if (rowForOrder <= 0) {
			return ResponseVo.error(ERROR);
		}
		int rowForItem = orderItemMapper.batchInsert(orderItemList);
		if (rowForItem <= 0) {
			return ResponseVo.error(ERROR);
		}
		// 更新购物车（删除选中的商品）
		// Redis 有事物（打包命令），不能回滚
		for (Cart cart : cartList) {
			cartService.delete(uid, cart.getProductId());
		}
		// 构造OrderVo
		OrderVo orderVo = builderOrderVo(order, orderItemList, shipping);
		return ResponseVo.success(orderVo);
	}

	private OrderVo builderOrderVo(Order order, List<OrderItem> orderItemList, Shipping shipping) {
		OrderVo orderVo = new OrderVo();
		BeanUtils.copyProperties(order, orderVo);

		List<OrderItemVo> orderVoList = orderItemList.stream().map(e -> {
			OrderItemVo orderItemVo = new OrderItemVo();
			BeanUtils.copyProperties(e, orderItemVo);
			return orderItemVo;
		}).collect(Collectors.toList());
		orderVo.setItemVoList(orderVoList);
		orderVo.setShippingId(shipping.getId());
		orderVo.setShippingVo(shipping);
		return orderVo;
	}

	private Order builderOrder(Integer uid, Long orderNo, Integer shippingId,
			List<OrderItem> orderItemList) {
		BigDecimal payment = orderItemList.stream().map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		Order order = new Order();
		order.setUserId(uid);
		order.setOrderNo(orderNo);
		order.setShippingId(shippingId);
		order.setPayment(payment);
		order.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
		order.setPostage(0);
		order.setStatus(OrderStatusEnum.NO_PAY.getCode());
		return order;
	}

	/**
	 * 企业级使用:分布式唯一id/主键
	 */
	private Long generateOrderNo() {
		return System.currentTimeMillis() + new Random().nextInt(999);
	}

	private OrderItem builderOrderItem(Integer uid, Long orderNo, Integer quantity,
			Product product) {
		OrderItem item = new OrderItem();
		item.setUserId(uid);
		item.setOrderNo(orderNo);
		item.setProductId(product.getId());
		item.setProductName(product.getName());
		item.setProductImage(product.getMainImage());
		item.setCurrentUnitPrice(product.getPrice());
		item.setQuantity(quantity);
		item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
		return item;
	}

}
