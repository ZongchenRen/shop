package cn.gogosoft.mall.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.form.CartUpdateForm;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-03-01 01:21
 * @description 购物车
 */
@Slf4j
public class CartServiceImplTest extends MallApplicationTests {

	@Autowired
	private CartServiceImpl cartService;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private Integer uid = 1;
	private Integer productId = 26;

	@Before
	public void add() {
		log.info("新增购物车...");
		CartAddForm cartAddForm = new CartAddForm();
		cartAddForm.setProductId(productId);
		cartAddForm.setSelected(true);
		ResponseVo<CartVo> responseVo = cartService.add(uid, cartAddForm);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
	}

	// 最后执行删除
	@After
	public void delete() {
		log.info("删除购物车...");
		ResponseVo<CartVo> list = cartService.delete(uid, productId);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
		log.info("list={}", gson.toJson(list));
	}

	@Test
	public void list() {
		ResponseVo<CartVo> list = cartService.list(uid);
		log.info("list={}", gson.toJson(list));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
	}

	@Test
	public void update() {
		CartUpdateForm form = new CartUpdateForm(3, false);
		ResponseVo<CartVo> list = cartService.update(uid, productId, form);
		log.info("list={}", gson.toJson(list));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
	}

	@Test
	public void selectAll() {
		ResponseVo<CartVo> list = cartService.selectAll(uid);
		log.info("list={}", gson.toJson(list));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
	}

	@Test
	public void unSelectAll() {
		ResponseVo<CartVo> list = cartService.unSelectAll(uid);
		log.info("list={}", gson.toJson(list));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
	}

	@Test
	public void sum() {
		ResponseVo<Integer> list = cartService.sum(uid);
		log.info("list={}", gson.toJson(list));
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
	}
}