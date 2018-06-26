package com.i2max.bootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.i2max.bootweb.controller.interceptor.LoggerInterceptor; 

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.i2max.bootweb" })
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier(value = "httpInterceptor")
	private HandlerInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		registry.addInterceptor(new LoggerInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**"); 
		
		 
	}
}