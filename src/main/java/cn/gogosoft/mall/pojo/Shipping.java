package cn.gogosoft.mall.pojo;

import java.util.Date;

import lombok.Data;

/**
 * 购物车
 */
@Data
public class Shipping {
	private Integer id;

	private Integer userId;

	private String receiverName;
	/**
	 * 固话
	 */
	private String receiverPhone;

	/**
	 * 手机
	 */
	private String receiverMobile;
	/**
	 * 省
	 */
	private String receiverProvince;
	/**
	 * 市
	 */
	private String receiverCity;
	/**
	 * 区/县
	 */
	private String receiverDistrict;
	/**
	 * 详细地址
	 */
	private String receiverAddress;
	/**
	 * 邮编
	 */
	private String receiverZip;

	private Date createTime;

	private Date updateTime;

}