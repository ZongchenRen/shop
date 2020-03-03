package cn.gogosoft.mall.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.form.OrderCreateForm;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.impl.OrderServiceImpl;
import cn.gogosoft.mall.vo.OrderVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-03-03 16:15
 * @description 订单
 */
@RestController
public class OrderController {
	@Autowired
	private OrderServiceImpl orderService;

	@PostMapping("/orders")
	public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
			HttpSession session) {
		User user = (User) session.getAttribute(MallConst.CURRENT_USER);
		ResponseVo<OrderVo> voResponseVo = orderService.create(user.getId(), form.getShippingid());
		return voResponseVo;
	}

	@GetMapping("/orders")
	public ResponseVo<PageInfo> list(@Valid @RequestBody OrderCreateForm form, HttpSession session,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		User user = (User) session.getAttribute(MallConst.CURRENT_USER);
		ResponseVo<PageInfo> voResponseVo = orderService.list(user.getId(), pageNum, pageSize);
		return voResponseVo;
	}

	@GetMapping("/orders/{orderNo}")
	public ResponseVo<OrderVo> detail(@PathVariable("orderNo") Long orderNo, HttpSession session) {
		User user = (User) session.getAttribute(MallConst.CURRENT_USER);
		ResponseVo<OrderVo> voResponseVo = orderService.detail(user.getId(), orderNo);
		return voResponseVo;
	}

	@PutMapping("/orders/{orderNo}")
	public ResponseVo<OrderVo> cancle(@PathVariable("orderNo") Long orderNo, HttpSession session) {
		User user = (User) session.getAttribute(MallConst.CURRENT_USER);
		ResponseVo voResponseVo = orderService.cancle(user.getId(), orderNo);
		return voResponseVo;
	}

}
