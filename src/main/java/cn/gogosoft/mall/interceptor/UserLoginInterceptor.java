package cn.gogosoft.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import cn.gogosoft.mall.consts.MallConst;
import cn.gogosoft.mall.exception.UserLoginExcption;
import cn.gogosoft.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renzongchen
 * @data 2020-02-26 17:30
 * @description 用户拦截器
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
	/**
	 * true表示继续流程 ;false 表示中断
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		log.info("preHandle...");
		HttpSession session = request.getSession();
		User user = ((User) session.getAttribute(MallConst.CURRENT_USER));
		if (user == null) {
			log.info("user=null");
			// 前端返回数据：方法1
			// response.getWriter().print("error");
			// 方法2：抛异常
			throw new UserLoginExcption();
			// return false;
			// return ResponseVo.error(NEED_LOGIN);
		}
		return true;
	}
}
