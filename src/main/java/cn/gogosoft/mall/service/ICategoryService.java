package cn.gogosoft.mall.service;

import java.util.List;

import cn.gogosoft.mall.vo.CategoryVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-26 21:35
 * @description 类目接口
 */
public interface ICategoryService {
	ResponseVo<List<CategoryVo>> selectAll();
}
