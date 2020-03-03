package cn.gogosoft.mall.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
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
@Transactional
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

	@Test
	public void list() {
		ResponseVo<PageInfo> responseVo = orderService.list(uid, 1, 5);
		log.info("result = {}", gson.toJson(responseVo));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

	}

	@Test
	public void detail() {
		ResponseVo<OrderVo> create = create2();
		ResponseVo<OrderVo> responseVo = orderService.detail(uid, create.getData().getOrderNo());
		log.info("detail = {}", gson.toJson(responseVo));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	private ResponseVo<OrderVo> create2() {
		ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
		log.info("create2 = {}", gson.toJson(responseVo));
		return responseVo;
	}

	@Test
	public void cancle() {
		ResponseVo<OrderVo> create = create2();
		ResponseVo responseVo = orderService.cancle(uid, create.getData().getOrderNo());
		log.info("cancle = {}", gson.toJson(responseVo));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}
}