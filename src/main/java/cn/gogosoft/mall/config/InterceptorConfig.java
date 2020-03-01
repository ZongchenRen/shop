package cn.gogosoft.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.gogosoft.mall.interceptor.UserLoginInterceptor;

/**
 * @author renzongchen
 * @data 2020-02-26 19:49
 * @description Interceptor是基于URL进行配置的
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/error", "/user/login", "/user/register", "/categories",
						"/products", "/products/*");

	}
}
