package cn.gogosoft.mall.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.RoleEnum;
import cn.gogosoft.mall.pojo.User;

/**
 * @author renzongchen
 * @data 2020-02-24 10:00
 * @description 用户测试
 */
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
	@Autowired
	private UserServiceImpl userService;

	@Test
	public void regist() {
		User user = new User("Jack", "111111", "123131@163.com", RoleEnum.CUSTOMER.getCode());
		userService.regist(user);
	}
}