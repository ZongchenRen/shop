package cn.gogosoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gogosoft.mall.pojo.Order;

public interface OrderMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	List<Order> selectByUid(@Param("uid") Integer uid);

	Order selectByOrderNo(@Param("orderNo") Long orderNo);

}