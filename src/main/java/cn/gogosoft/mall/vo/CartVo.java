package cn.gogosoft.mall.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-29 22:14
 * @description 购物车
 */
@Data
public class CartVo {
	private List<CartProductVo> cartProductVo;
	/**
	 * 是否全选
	 */
	private Boolean selectAll;
	/**
	 * 总价
	 */
	private BigDecimal cartTotalPrice;
	/**
	 * 总数量
	 */
	private Integer cartTotalQuantity;
}
