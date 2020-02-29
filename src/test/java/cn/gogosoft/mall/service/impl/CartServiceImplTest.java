package cn.gogosoft.mall.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.form.CartAddForm;
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

	@Test
	public void add() {
		CartAddForm cartAddForm = new CartAddForm();
		cartAddForm.setProductId(26);
		cartAddForm.setSelected(true);
		cartService.add(1, cartAddForm);
	}
}