package cn.gogosoft.mall.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.gogosoft.mall.pojo.Product;

public interface ProductMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Product record);

	int insertSelective(Product record);

	Product selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);

	// CategoryIdSet 和xml中的collection对应
	List<Product> selectByCategoryIdSet(@Param("categoryIdSet") Set<Integer> categoryIdSet);

	// CategoryIdSet 和xml中的collection对应
	List<Product> selectByProductIdSet(@Param("productIdSet") Set<Integer> productIdSet);
}