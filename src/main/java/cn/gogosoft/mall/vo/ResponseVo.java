package cn.gogosoft.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.gogosoft.mall.enums.ResponseEnum;
import lombok.Data;

/**
 * @author renzongchen
 * @data 2020-02-24 11:33
 * @description
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class ResponseVo<T> {
	private Integer status;
	private String msg;
	private T data;

	public ResponseVo(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static <T> ResponseVo<T> success(String msg) {
		return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), msg);
	}

	public static <T> ResponseVo<T> success() {
		return success(ResponseEnum.SUCCESS.getDesc());
	}

	public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
		return new ResponseVo<>(responseEnum.getCode(), responseEnum.getDesc());
	}
}
