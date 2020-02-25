package cn.gogosoft.mall.dao;

import cn.gogosoft.mall.pojo.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	int countByUsername(String username);

	int countByEmail(String email);
}