package cn.gogosoft.mall.controller;

import static cn.gogosoft.mall.enums.ResponseEnum.PARAM_ERROR;

import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.form.UserLoginForm;
import cn.gogosoft.mall.form.UserRegisterForm;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.impl.UserServiceImpl;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-24 11:09
 * @description
 */
@RestController
@Slf4j
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/user/register")
	public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("注册提交的参数有误,{} {}",
					Objects.requireNonNull(bindingResult.getFieldError()).getField(),
					bindingResult.getFieldError().getDefaultMessage());
			return ResponseVo.error(PARAM_ERROR, bindingResult);
		}
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		return userService.regist(user);
	}

	@PostMapping("/user/login")
	public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return ResponseVo.error(PARAM_ERROR, bindingResult);
		}
		ResponseVo responseVo = userService.login(userLoginForm.getUsername(),
				userLoginForm.getPassword());
		// 设置session
		session.setAttribute(MallConst.CURRENT_USER, responseVo.getData());
		return responseVo;
	}

	// session 保存在内存中，容易丢失 可以存储在redis中，->token+redis
	@GetMapping("/user")
	public ResponseVo<User> userInfo(HttpSession session) {
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		// 拦截器统一处理 {@link UserLoginInterceptor}
		// if (user == null) {
		// return ResponseVo.error(NEED_LOGIN);
		// }
		return ResponseVo.success(user);
	}

	// TODO 判断状态 统一拦截
	@PostMapping("/user/logout")
	/**
	 * {@link TomcatServletWebServerFactory}
	 */
	public ResponseVo<User> userLogOut(HttpSession session) {
		session.removeAttribute(MallConst.CURRENT_USER);
		return ResponseVo.success();
	}

}
