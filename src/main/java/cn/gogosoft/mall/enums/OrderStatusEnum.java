package cn.gogosoft.mall.enums;

import lombok.Getter;

/**
 * @author renzongchen
 * @data 2020-03-03 00:30
 * @description 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
 */
@Getter
public enum OrderStatusEnum {

	/**
	 * 已取消
	 */
	CANCELED(0, "已取消"),

	/**
	 * 未付款
	 */
	NO_PAY(10, "未付款"),

	/**
	 * 已付款
	 */
	PAID(20, "已付款"),

	/**
	 * 已发货
	 */
	SHIPPED(40, "已发货"),

	/**
	 * 交易成功
	 */
	TRADE_SUCCESS(50, "交易成功"),

	/**
	 * 交易管理
	 */
	TRADE_CLOSE(60, "交易管理"),;

	Integer code;
	String desc;

	OrderStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
