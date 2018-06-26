package com.i2max.bootweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
 
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter  {
	  
    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);  
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		final String remoteHost = request.getRemoteHost();
		final String remoteAddr = request.getRemoteAddr();
		final String forwardedProto = request.getHeader("x-forwarded-proto");
		logger.info("remoteHost : " + remoteHost);
		
		logger.info("remoteAddr : " + remoteAddr);
		
		if (logger.isDebugEnabled()) {
			logger.debug("======================================          START1         ======================================");
			logger.debug(" Request URI \t:  " + request.getRequestURI());
		}
		

     
    	/*******************************************************************
    	 * HTTPS 일 경우 만 처리
    	 ******************************************************************/ 
    	if( null != forwardedProto ) 
        {
    		if( true == request.getHeader("x-forwarded-proto").equalsIgnoreCase("https"))
    		{

    			logger.debug("======================================  x-forwarded-proto Is https");
    			return super.preHandle(request, response, handler);
    		}
        }
 
    	logger.debug("======================================  x-forwarded-proto Is Not https Redirect Error Page");
	 //  response.sendRedirect("/DefaultErrorPage.jsp");
     //  return false;
     
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("======================================           END1          ======================================\n");
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		logger.info("================ Method Completed");
	}
}