package cn.gogosoft.mall.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-25 22:46
 * @description 注册表单用
 */
@Data
public class UserRegisterForm {
	// @NotNull // 不能为空
	// @NotEmpty // 集合
	@NotBlank(message = "用户名不能为空")
	private String username;

	@NotBlank(message = "密码不能为空")
	private String password;

	@NotBlank(message = "邮箱不能为空")
	private String email;

}
