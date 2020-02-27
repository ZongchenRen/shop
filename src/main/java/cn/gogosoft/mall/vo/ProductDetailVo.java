package cn.gogosoft.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-28 00:10
 * @description 详情
 */
@Data
public class ProductDetailVo {
	private Integer id;

	private Integer categoryId;

	private String name;

	private String subtitle;

	private String mainImage;

	private String subImages;

	private String detail;

	private BigDecimal price;

	private Integer stock;

	private Integer status;

	private Date createTime;

	private Date updateTime;
}
