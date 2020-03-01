package cn.gogosoft.mall.enums;

import lombok.Getter;

/**
 * @author renzongchen
 * @data 2020-02-24 11:48
 * @description 返回状态码
 */
@Getter
public enum ResponseEnum {
	/**
	 * 服务端错误
	 */
	ERROR(-1, "服务端错误"),
	/**
	 * 成功
	 */
	SUCCESS(0, "成功"),
	/**
	 *
	 */
	PASSWORD_ERROR(1, "密码错误"),
	/**
	*
	*/
	USERNAME_EXIT(2, "用户名已存在"),
	/**
	 * 参数错误
	 */
	PARAM_ERROR(3, "参数错误"),
	/**
	 * 邮箱已存在
	 */
	EMAIL_EXIT(4, "邮箱已存在"),
	/**
	 * 用户未登录,请先登录
	 */
	NEED_LOGIN(10, "用户未登录,请先登录"),
	/**
	 * 用户名或密码错误
	 */
	USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),
	/**
	 * 商品下架或删除
	 */
	PRODUCT_OFF_SALE_DELETE(12, "商品下架或删除"),
	/**
	 * 商品不存在
	 */
	PRODUCT_NOT_EXIST(13, "商品不存在"),
	/**
	 * 库存不足
	 */
	PRODUCT_STACK_ERROR(14, "库存不足"),
	/**
	 * 购物车中商品不存在
	 */
	CART_PRODUCT_NOT_EXIST(15, "购物车中无此商品"),;

	Integer code;
	String desc;

	ResponseEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
