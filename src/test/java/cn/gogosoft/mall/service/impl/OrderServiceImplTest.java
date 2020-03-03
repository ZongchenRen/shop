package cn.gogosoft.mall.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.OrderVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-03-02 21:07
 * @description 订单
 */
@Slf4j
public class OrderServiceImplTest extends MallApplicationTests {

	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private CartServiceImpl cartService;
	private Integer uid = 1;
	private Integer shippingId = 4;
	private Integer productId = 26;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Before
	public void before() {
		log.info("新增购物车...");
		CartAddForm cartAddForm = new CartAddForm();
		cartAddForm.setProductId(productId);
		cartAddForm.setSelected(true);
		ResponseVo<CartVo> responseVo = cartService.add(uid, cartAddForm);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	@Test
	public void create() {
		ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
		log.info("result = {}", gson.toJson(responseVo));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}
}