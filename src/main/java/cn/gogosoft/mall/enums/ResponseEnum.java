package cn.gogosoft.mall.enums;

import lombok.Getter;

/**
 * @author renzongchen
 * @data 2020-02-24 11:48
 * @description 返回状态码
 */
@Getter
public enum ResponseEnum {
	ERROR(-1, "服务端错误"), SUCCESS(0, "成功"), PASSWORD_ERROR(1, "密码错误"), USERNAME_EXIT(2,
			"用户名已存在"), PARAM_ERROR(3, "参数错误"), EMAIL_EXIT(4,
					"邮箱已存在"), NEED_LOGIN(10, "用户未登录,请先登录"), USERNAME_OR_PASSWORD_ERROR(11, "用户名或密码错误"),;

	Integer code;
	String desc;

	ResponseEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
