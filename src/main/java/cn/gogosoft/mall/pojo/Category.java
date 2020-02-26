package cn.gogosoft.mall.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class Category {
	private Integer id;

	private Integer parentId;

	private String name;

	private Boolean status;

	private Integer sortOrder;

	private Date createTime;

	private Date updateTime;

}