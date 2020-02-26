package cn.gogosoft.mall.service;

import cn.gogosoft.mall.pojo.User;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-24 09:32
 * @description 用户接口
 */
public interface IUserService {
	/**
	 * 用户注册
	 */
	ResponseVo<User> regist(User user);

	/**
	 * 用户登录
	 */
	ResponseVo<User> login(String username, String passwor);
}
