package cn.gogosoft.mall.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.gogosoft.mall.pojo.OrderItem;

public interface OrderItemMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(OrderItem record);

	int insertSelective(OrderItem record);

	OrderItem selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(OrderItem record);

	int updateByPrimaryKey(OrderItem record);

	int batchInsert(@Param("orderItemList") List<OrderItem> recordList);

	List<OrderItem> selectByOrderNoSet(@Param("orderNoSet") Set<Long> orderNoSet);

}