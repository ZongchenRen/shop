package cn.gogosoft.mall.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-03 16:17
 * @description
 */
@Data
public class OrderCreateForm {
	@NotNull
	private Integer shippingid;

}
