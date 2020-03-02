package cn.gogosoft.mall.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.form.ShippingForm;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.impl.ShippingServiceImpl;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-02 16:42
 * @description 地址
 */
@RestController
public class ShippingController {
	@Autowired
	private ShippingServiceImpl shippingService;

	@PostMapping("/shippings")
	public ResponseVo<Map<String, Integer>> add(@Valid @RequestBody ShippingForm form,
			HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return shippingService.add(user.getId(), form);
	}

	@DeleteMapping("/shippings/{shippingId}")
	public ResponseVo delete(@PathVariable("shippingId") Integer shippingId, HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return shippingService.delete(user.getId(), shippingId);
	}

	@PutMapping("/shippings/{shippingId}")
	public ResponseVo update(@PathVariable("shippingId") Integer shippingId, HttpSession session,
			@Valid @RequestBody ShippingForm form) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));

		return shippingService.update(user.getId(), shippingId, form);
	}

	@GetMapping("/shippings")
	public ResponseVo<PageInfo> list(HttpSession session,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		return shippingService.list(user.getId(), pageNum, pageSize);
	}

}
