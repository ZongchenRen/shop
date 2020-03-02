package cn.gogosoft.mall.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-03-02 13:25
 * @description 新增地址
 */
@Data
public class ShippingForm {

	@NotBlank
	private String receiverName;
	@NotBlank
	private String receiverPhone;
	@NotBlank
	private String receiverMobile;
	@NotBlank
	private String receiverProvince;
	@NotBlank
	private String receiverCity;
	@NotBlank
	private String receiverDistrict;
	@NotBlank
	private String receiverAddress;
	@NotBlank
	private String receiverZip;

}
