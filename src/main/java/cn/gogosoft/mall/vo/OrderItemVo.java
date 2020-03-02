package cn.gogosoft.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-02 20:14
 * @description 订单项vo
 */
@Data
public class OrderItemVo {

	private Long orderNo;

	private Integer productId;

	private String productName;

	private String productImage;

	private BigDecimal currentUnitPrice;

	private Integer quantity;

	private BigDecimal totalPrice;

	private Date createTime;
}
