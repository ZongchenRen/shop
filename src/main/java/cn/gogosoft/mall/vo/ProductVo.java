package cn.gogosoft.mall.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-27 11:16
 * @description 商品VO
 */
@Data
public class ProductVo {
	private Integer id;

	private Integer categoryId;

	private String name;

	private String subtitle;

	private String mainImage;

	private Integer status;

	private BigDecimal price;
}
