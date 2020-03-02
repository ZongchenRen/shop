package cn.gogosoft.mall.enums;

import lombok.Getter;

/**
 * @author renzongchen
 * @data 2020-03-03 00:28
 * @description
 */
@Getter
public enum PaymentTypeEnum {
	PAY_ONLINE(1),;

	Integer code;

	PaymentTypeEnum(Integer code) {
		this.code = code;
	}
}
