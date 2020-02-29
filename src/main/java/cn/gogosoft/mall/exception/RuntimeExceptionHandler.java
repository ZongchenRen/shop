package cn.gogosoft.mall.exception;

/**
 * @author renzongchen
 * @data 2020-02-25 23:47
 * @description
 */

import static cn.gogosoft.mall.enums.ResponseEnum.ERROR;
import static cn.gogosoft.mall.enums.ResponseEnum.NEED_LOGIN;

import java.util.Objects;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gogosoft.mall.enums.ResponseEnum;
import cn.gogosoft.mall.vo.ResponseVo;

@ControllerAdvice
public class RuntimeExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	// @ResponseStatus(value = HttpStatus.ACCEPTED) 返回状态码，跟返回数据没关系
	public ResponseVo handle(RuntimeException e) {
		return ResponseVo.error(ERROR, e.getMessage());
	}

	@ExceptionHandler(UserLoginExcption.class)
	@ResponseBody
	public ResponseVo userLoginHandle() {
		return ResponseVo.error(NEED_LOGIN);
	}

	/**
	 * 表单统一验证
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		Objects.requireNonNull(bindingResult.getFieldError());
		return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult.getFieldError().getField()
				+ " " + bindingResult.getFieldError().getDefaultMessage());

	}
}
