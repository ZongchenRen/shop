package cn.gogosoft.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author renzongchen
 * @data 2020-02-21 23:05
 * @description po(persistent object)持久层对象；pojo (plian ordinary java
 *              object)普通java对象
 */
@Data
public class Category {
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Date createTime;
	private Date updateTime;

}
