package cn.gogosoft.mall.controller;

import static cn.gogosoft.mall.enums.ResponseEnum.NEED_LOGIN;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-24 11:09
 * @description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@PostMapping("/register")
	public ResponseVo register(@RequestBody User user) {
		log.info("username={}" + user.toString());
		// return ResponseVo.success();
		return ResponseVo.error(NEED_LOGIN);
	}
}
