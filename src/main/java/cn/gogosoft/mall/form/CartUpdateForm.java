package cn.gogosoft.mall.form;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-01 20:11
 * @description 更新购物车
 */
@Data
public class CartUpdateForm {
	private Integer quantity;
	private Boolean selected;

	public CartUpdateForm(Integer quantity, Boolean selected) {
		this.quantity = quantity;
		this.selected = selected;
	}
}
