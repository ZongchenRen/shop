package cn.gogosoft.mall.vo;

import java.util.List;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-26 21:31
 * @description TODO
 */
@Data
public class CategoryVo {
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	// 递归
	private List<CategoryVo> subCategories;
}
