package cn.gogosoft.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.service.impl.ProductServiceImpl;
import cn.gogosoft.mall.vo.ProductDetailVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-27 15:35
 * @description 商品
 */
@RestController
@Slf4j
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;

	@GetMapping("/products")
	public ResponseVo<PageInfo> list(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(required = false, value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {
		return productService.list(categoryId, pageNum, pageSize);

	}

	@GetMapping("/products/{productId}")
	public ResponseVo<ProductDetailVo> detail(@PathVariable("productId") Integer productId) {
		return productService.detail(productId);
	}

}
