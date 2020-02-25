package cn.gogosoft.mall.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.gogosoft.mall.pojo.Category;

/**
 * @author renzongchen
 * @data 2020-02-21 23:12
 * @description 开发生成器思路：链接数据库 -> 获取表结构 -> 生成文件
 */
// @Mapper
public interface CategoryMapper {

	@Select("select * from mall_category where id=#{id}")
	Category findById(@Param("id") Integer id);

	Category queryById(@Param("id") Integer id);
}
