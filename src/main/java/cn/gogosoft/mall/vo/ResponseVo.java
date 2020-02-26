package cn.gogosoft.mall.vo;

import java.util.Objects;

import org.springframework.validation.BindingResult;

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

	private ResponseVo(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private ResponseVo(Integer status, T data) {
		this.status = status;
		this.data = data;
	}

	private static <T> ResponseVo<T> successByMsg(String msg) {
		return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), msg);
	}

	public static <T> ResponseVo<T> success() {
		return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
	}

	public static <T> ResponseVo<T> success(T data) {
		return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), data);
	}

	public static <T> ResponseVo<T> successByMsg() {
		return successByMsg(ResponseEnum.SUCCESS.getDesc());
	}

	public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
		return new ResponseVo<>(responseEnum.getCode(), responseEnum.getDesc());
	}

	/**
	 * 参数传值异常
	 */
	public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg) {
		return new ResponseVo<>(responseEnum.getCode(), msg);
	}

	/**
	 * 参数传值异常
	 */
	public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
		return new ResponseVo<>(responseEnum.getCode(),
				Objects.requireNonNull(bindingResult.getFieldError()).getField() + " "
						+ bindingResult.getFieldError().getDefaultMessage());
	}
}
