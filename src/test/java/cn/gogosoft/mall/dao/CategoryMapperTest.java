package cn.gogosoft.mall.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.pojo.Category;

/**
 * @author renzongchen
 * @data 2020-02-22 14:04
 * @description TODO
 */

public class CategoryMapperTest extends MallApplicationTests {
	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void findById() {
		Category category = categoryMapper.findById(100001);
		System.err.println(category);
	}

	@Test
	public void queryById() {
		Category category = categoryMapper.queryById(100002);
		System.err.println(category);
	}
}