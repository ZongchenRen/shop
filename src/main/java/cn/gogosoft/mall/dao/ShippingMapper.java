package cn.gogosoft.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gogosoft.mall.pojo.Shipping;

public interface ShippingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Shipping record);

	int insertSelective(Shipping record);

	Shipping selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Shipping record);

	int updateByPrimaryKey(Shipping record);

	int deleteByIdAndUid(@Param("uid") Integer uid, @Param("shippingId") Integer shippingId);

	List<Shipping> selectByUid(@Param("uid") Integer uid);

}