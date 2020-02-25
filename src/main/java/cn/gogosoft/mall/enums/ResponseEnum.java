package cn.gogosoft.mall.enums;

import lombok.Getter;

/**
 * @author renzongchen
 * @data 2020-02-24 11:48
 * @description 返回状态码
 */
@Getter
public enum ResponseEnum {
	ERROR(-1, "服务端错误"), SUCCESS(0, "成功"), PASSWORD_ERROR(1, "密码错误"), USER_EXIT(2,
			"用户已存在"), PARAM_ERROR(3, "参数错误"), NEED_LOGIN(10, "用户未登录,请先登录"),;

	Integer code;
	String desc;

	ResponseEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
