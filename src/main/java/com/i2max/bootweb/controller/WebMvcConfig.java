package com.i2max.bootweb.controller;
 
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.i2max.bootweb.controller.interceptor.LoggerInterceptor; 

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.i2max.bootweb" })
public class WebMvcConfig implements WebMvcConfigurer { 
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		registry.addInterceptor(new LoggerInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**");  
	}
	
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
 
}