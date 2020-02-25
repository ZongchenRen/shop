package cn.gogosoft.mall.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.pojo.Order;

/**
 * @author renzongchen
 * @data 2020-02-22 16:42
 * @description TODO
 */
public class OrderMapperTest extends MallApplicationTests {
	@Autowired
	OrderMapper orderMapper;

	@Test
	public void deleteByPrimaryKey() {
	}

	@Test
	public void insert() {
	}

	@Test
	public void insertSelective() {
	}

	@Test
	public void selectByPrimaryKey() {
		Order order = orderMapper.selectByPrimaryKey(10000);
		System.out.println(order);

	}

	@Test
	public void updateByPrimaryKeySelective() {
	}

	@Test
	public void updateByPrimaryKey() {
	}
}