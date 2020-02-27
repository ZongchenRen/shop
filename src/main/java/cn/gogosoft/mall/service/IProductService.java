package cn.gogosoft.mall.service;

import com.github.pagehelper.PageInfo;

import cn.gogosoft.mall.vo.ProductDetailVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-27 14:22
 * @description
 */
public interface IProductService {
	ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

	ResponseVo<ProductDetailVo> detail(Integer productId);
}
