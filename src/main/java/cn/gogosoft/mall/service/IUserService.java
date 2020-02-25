package cn.gogosoft.mall.service;

import cn.gogosoft.mall.pojo.User;

/**
 * @author renzongchen
 * @data 2020-02-24 09:32
 * @description 用户接口
 */
public interface IUserService {
	/**
	 * 用户注册
	 */
	void regist(User user);

	/**
	 * 用户登录
	 */
}
