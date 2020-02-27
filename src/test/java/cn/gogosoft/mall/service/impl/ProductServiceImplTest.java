package cn.gogosoft.mall.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.MallApplicationTests;
import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-27 14:41
 * @description 商品测试
 */
@Slf4j
@Transactional
public class ProductServiceImplTest extends MallApplicationTests {
	@Autowired
	private ProductServiceImpl productService;

	@Test
	public void list() {
		ResponseVo<PageInfo> list = productService.list(null, 2, 3);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());

	}
}