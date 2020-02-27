package cn.gogosoft.mall.service.impl;

import static cn.gogosoft.mall.enums.ProductStatusEnum.DELETE;
import static cn.gogosoft.mall.enums.ProductStatusEnum.OFF_SALE;
import static cn.gogosoft.mall.enums.ResponseEnum.PRODUCT_OFF_SALE_DELETE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.dao.ProductMapper;
import cn.gogosoft.mall.pojo.Product;
import cn.gogosoft.mall.service.IProductService;
import cn.gogosoft.mall.vo.ProductDetailVo;
import cn.gogosoft.mall.vo.ProductVo;
import cn.gogosoft.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-27 14:22
 * @description
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
		Set<Integer> categoryIdSet = new HashSet<>();
		if (categoryId != null) {
			categoryService.findSubCategoryId(categoryId, categoryIdSet);
			// 包含自身id
			categoryIdSet.add(categoryId);
		}
		// List<Product> products = productMapper.selectByCategoryIdSet(categoryIdSet);
		PageHelper.startPage(pageNum, pageSize);
		List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
		List<ProductVo> productVoList = productList.stream().map(e -> {
			ProductVo productVo = new ProductVo();
			BeanUtils.copyProperties(e, productVo);
			return productVo;
		}).collect(Collectors.toList());
		PageInfo pageInfo = new PageInfo<>(productList);
		pageInfo.setList(productVoList);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<ProductDetailVo> detail(Integer productId) {
		Product product = productMapper.selectByPrimaryKey(productId);
		if (product == null) {
			return null;
		}
		// 只对确定性的进行判断
		if (product.getStatus().equals(OFF_SALE.getCode())
				|| product.getStatus().equals(DELETE.getCode())) {
			return ResponseVo.error(PRODUCT_OFF_SALE_DELETE);
		}
		ProductDetailVo productVo = new ProductDetailVo();
		BeanUtils.copyProperties(product, productVo);
		// 敏感数据处理
		productVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
		return ResponseVo.success(productVo);
	}
}
