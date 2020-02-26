package cn.gogosoft.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gogosoft.mall.service.impl.CategoryServiceImpl;
import cn.gogosoft.mall.vo.CategoryVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-26 21:30
 * @description 类目
 */
@RestController
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryService;

	@GetMapping("/categories")
	public ResponseVo<List<CategoryVo>> categories() {
		return categoryService.selectAll();
	}

}
