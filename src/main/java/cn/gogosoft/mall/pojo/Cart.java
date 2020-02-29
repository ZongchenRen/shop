package cn.gogosoft.mall.pojo;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-01 01:11
 * @description 购物车
 */
@Data
public class Cart {
	private Integer productId;
	private Integer quantity;
	private Boolean productSelected;

	public Cart() {
	}

	public Cart(Integer productId, Integer quantity, Boolean productSelected) {
		this.productId = productId;
		this.quantity = quantity;
		this.productSelected = productSelected;
	}
}
