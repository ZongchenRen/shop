package cn.gogosoft.mall.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-29 23:32
 * @description 购物车
 */
@RestController
public class CartController {
	@PostMapping("/carts")
	public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm) {
		return null;
	}
}
