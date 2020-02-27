package cn.gogosoft.mall.service;

import java.util.List;
import java.util.Set;

import cn.gogosoft.mall.vo.CategoryVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * The interface Category service.
 *
 * @author renzongchen
 * @data 2020 -02-26 21:35
 * @description 类目接口
 */
public interface ICategoryService {

	/**
	 * Select all response vo.
	 *
	 * @return response vo
	 */
	ResponseVo<List<CategoryVo>> selectAll();

	/**
	 * Find sub category id.
	 *
	 * @param id
	 *           the id
	 * @param resultSet
	 *           the result set
	 */
	void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
