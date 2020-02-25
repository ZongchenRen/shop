package cn.gogosoft.mall.service.impl;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.gogosoft.mall.dao.UserMapper;
import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.service.IUserService;

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
	public void regist(User user) {
		// 1.username 不能重复
		int countByUsername = userMapper.countByUsername(user.getUsername());
		if (countByUsername > 0) {
			throw new RuntimeException("该Username已经注册！");
		}
		// 2.email不能重复
		int countByEmail = userMapper.countByEmail(user.getEmail());
		if (countByEmail > 0) {
			throw new RuntimeException("该Email已经被注册！");
		}
		// MD5(摘要算法) Spring自带
		user.setPassword(
				DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
		// 数据写入数据库
		int num = userMapper.insertSelective(user);
		if (num == 0) {
			throw new RuntimeException("注册失败！");
		}
	}
}
