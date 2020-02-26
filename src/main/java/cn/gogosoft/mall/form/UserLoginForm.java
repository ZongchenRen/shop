package cn.gogosoft.mall.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-26 01:05
 * @description 用户登录
 */
@Data
public class UserLoginForm {

	@NotBlank(message = "请输入正确的用户名")
	private String username;
	@NotBlank(message = "请输入正确的密码")
	private String password;

}
