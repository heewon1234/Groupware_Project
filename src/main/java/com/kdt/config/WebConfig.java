package com.kdt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kdt.interceptors.LoginValidator;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:e:/uploads/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getLoginValidator())
				.excludePathPatterns("/")
				.excludePathPatterns("/err")
				.excludePathPatterns("/members/login")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/uploads/**");
	}
	
	@Bean
	public LoginValidator getLoginValidator() {
		return new LoginValidator();
	}
}
