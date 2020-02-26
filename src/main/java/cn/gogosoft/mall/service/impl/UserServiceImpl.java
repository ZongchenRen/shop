package cn.gogosoft.mall.service.impl;

import static cn.gogosoft.mall.enums.ResponseEnum.*;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.gogosoft.mall.dao.UserMapper;
import cn.gogosoft.mall.enums.RoleEnum;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.IUserService;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-24 09:36
 * @description 用户实现类
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户注册
	 *
	 * @param user
	 */
	@Override
	public ResponseVo<User> regist(User user) {
		// error(); //测试异常
		// 1.username 不能重复
		int countByUsername = userMapper.countByUsername(user.getUsername());
		if (countByUsername > 0) {
			return ResponseVo.error(USERNAME_EXIT);
		}
		// 2.email不能重复
		int countByEmail = userMapper.countByEmail(user.getEmail());
		if (countByEmail > 0) {
			return ResponseVo.error(EMAIL_EXIT);
		}

		user.setRole(RoleEnum.CUSTOMER.getCode());

		// MD5(摘要算法) Spring自带
		user.setPassword(
				DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
		// 数据写入数据库
		int num = userMapper.insertSelective(user);
		if (num == 0) {
			return ResponseVo.error(ERROR);
		}
		return ResponseVo.error(SUCCESS);
	}

	/**
	 * 用户登录
	 *
	 * @param username
	 * @param password
	 */
	@Override
	public ResponseVo<User> login(String username, String password) {
		User user = userMapper.selectByUsername(username);
		if (user == null || !DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8))
				.equalsIgnoreCase(user.getPassword())) {
			// 返回 用户名或密码错误
			return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
		}
		user.setPassword("");
		return ResponseVo.success(user);
	}

	private void error() {
		throw new RuntimeException("意外错误");
	}
}
