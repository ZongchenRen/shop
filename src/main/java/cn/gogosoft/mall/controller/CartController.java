package cn.gogosoft.mall.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.form.CartAddForm;
import cn.gogosoft.mall.form.CartUpdateForm;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.impl.CartServiceImpl;
import cn.gogosoft.mall.vo.CartVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-29 23:32
 * @description 购物车
 */
@RestController
public class CartController {
	@Autowired
	private CartServiceImpl cartService;

	@GetMapping("/carts")
	public ResponseVo<CartVo> list(HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.list(user.getId());
	}

	@PostMapping("/carts")
	public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm, HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.add(user.getId(), cartAddForm);
	}

	@PutMapping("/carts/{productId}")
	public ResponseVo<CartVo> update(@PathVariable("productId") Integer productId,
			@Valid @RequestBody CartUpdateForm form, HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.update(user.getId(), productId, form);
	}

	@DeleteMapping("/carts/{productId}")
	public ResponseVo<CartVo> delete(@PathVariable("productId") Integer productId,
			HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.delete(user.getId(), productId);
	}

	@PutMapping("/carts/selectAll")
	public ResponseVo<CartVo> selectAll(HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.selectAll(user.getId());
	}

	@PutMapping("/carts/unSelectAll")
	public ResponseVo<CartVo> unSelectAll(HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.unSelectAll(user.getId());
	}

	@GetMapping("/carts/products/sum")
	public ResponseVo<Integer> sum(HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return cartService.sum(user.getId());
	}
}
