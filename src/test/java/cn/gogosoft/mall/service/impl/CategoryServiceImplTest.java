package cn.gogosoft.mall.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.vo.CategoryVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-27 00:34
 * @description 类目单元测试
 */
@Transactional
@Slf4j
public class CategoryServiceImplTest extends MallApplicationTests {
	@Autowired
	CategoryServiceImpl categoryService;

	@Test
	public void selectAll() {
		ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
	}

	@Test
	public void findSubCategoryId() {
		Set<Integer> set = new HashSet<Integer>();
		categoryService.findSubCategoryId(100001, set);
		log.error("目录={}" + set);
	}
}