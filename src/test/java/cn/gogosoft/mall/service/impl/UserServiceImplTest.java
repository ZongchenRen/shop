package cn.gogosoft.mall.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.enums.RoleEnum;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-24 10:00
 * @description 用户测试
 */
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
	@Autowired
	private UserServiceImpl userService;
	public static String USERNAME = "JACK";
	public static String PASSWORD = "123456";

	@Before
	public void regist() {
		User user = new User(USERNAME, PASSWORD, "123131@163.com", RoleEnum.CUSTOMER.getCode());
		userService.regist(user);
	}

	@Test
	public void login() {
		ResponseVo<User> user = userService.login(USERNAME, PASSWORD);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), user.getStatus());
	}
}