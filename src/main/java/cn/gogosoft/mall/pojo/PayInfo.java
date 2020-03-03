package cn.gogosoft.mall.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PayInfo {
	private Integer id;

	private Integer userId;

	private Long orderNo;

	private Integer payPlatform;

	private String platformNumber;

	private String platformStatus;

	private BigDecimal payAmount;

	private Date createTime;

	private Date updateTime;

}