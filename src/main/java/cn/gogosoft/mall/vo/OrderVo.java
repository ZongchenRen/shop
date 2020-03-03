package cn.gogosoft.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.gogosoft.mall.pojo.Shipping;
import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-02 20:13
 * @description 订单VO
 */
@Data
public class OrderVo {

	private Long orderNo;

	private BigDecimal payment;

	private Integer paymentType;

	private Integer postage;

	private Integer status;

	private Date paymentTime;

	private Date sendTime;

	private Date endTime;

	private Date closeTime;

	private Date createTime;

	private List<OrderItemVo> orderItemVoList;

	private Integer shippingId;

	private Shipping shippingVo;

}
