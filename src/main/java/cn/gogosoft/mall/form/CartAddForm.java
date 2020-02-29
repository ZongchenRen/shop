package cn.gogosoft.mall.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-29 23:30
 * @description 添加商品表单
 */
@Data
public class CartAddForm {
	@NotNull
	private Integer productId;
	private Boolean selected = true;
}
