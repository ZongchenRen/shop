package cn.gogosoft.mall.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.dao.CategoryMapper;
import cn.gogosoft.mall.pojo.Category;
import cn.gogosoft.mall.service.ICategoryService;
import cn.gogosoft.mall.vo.CategoryVo;
import cn.gogosoft.mall.vo.ResponseVo;

/**
 * @author renzongchen
 * @data 2020-02-26 21:36
 * @description 类目服务
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public ResponseVo<List<CategoryVo>> selectAll() {
		List<Category> categories = categoryMapper.selectAll();
		// 查询parentId=0
		// for (Category category : categories) {
		// if (MallConst.ROOT_PARENT_ID.equals(category.getParentId())) {
		// CategoryVo categoryVo = new CategoryVo();
		// BeanUtils.copyProperties(category, categoryVo);
		// categoryVoList.add(categoryVo);
		// }
		// }
		// lambda + stream
		List<CategoryVo> categoryVoList = categories.stream()
				.filter(e -> MallConst.ROOT_PARENT_ID.equals(e.getParentId()))
				.map(this::category2CategoryVo).collect(Collectors.toList());
		categoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());

		// 子目录
		findSubCategory(categoryVoList, categories);
		return ResponseVo.success(categoryVoList);
	}

	@Override
	public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
		List<Category> categories = categoryMapper.selectAll();
		findSubCategoryId(id, resultSet, categories);
	}

	// 递归
	private void findSubCategoryId(Integer id, Set<Integer> resultSet, List<Category> categories) {
		for (Category category : categories) {
			if (id.equals(category.getParentId())) {
				resultSet.add(category.getId());
				findSubCategoryId(category.getId(), resultSet, categories);
			}

		}
	}

	/**
	 * 查询子目录
	 *
	 * @param categoryVoList
	 *           上级目录
	 * @param categories
	 *           数据源
	 */
	private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
		for (CategoryVo categoryVo : categoryVoList) {
			List<CategoryVo> subCategoryVoList = new ArrayList<>();
			for (Category category : categories) {
				// 查到内容，设置subCategory，继续往下查
				if (categoryVo.getId().equals(category.getParentId())) {
					CategoryVo categoryVo1 = category2CategoryVo(category);
					subCategoryVoList.add(categoryVo1);
				}
				// 先对子目录排序
				subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
				categoryVo.setSubCategories(subCategoryVoList);
				findSubCategory(subCategoryVoList, categories);
			}
		}

	}

	private CategoryVo category2CategoryVo(Category category) {
		CategoryVo categoryVo = new CategoryVo();
		BeanUtils.copyProperties(category, categoryVo);
		return categoryVo;
	}
}
