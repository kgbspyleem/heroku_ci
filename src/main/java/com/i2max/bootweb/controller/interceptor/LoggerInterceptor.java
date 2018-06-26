package com.i2max.bootweb.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	private static final Log log = LogFactory.getLog(LoggerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		final String remoteHost = request.getRemoteHost();
		final String remoteAddr = request.getRemoteAddr();
		final String forwardedProto = request.getHeader("x-forwarded-proto");
		log.info("remoteHost : " + remoteHost);
		
		log.info("remoteAddr : " + remoteAddr);
		
		if (log.isDebugEnabled()) {
			log.debug("======================================          START1         ======================================");
			log.debug(" Request URI \t:  " + request.getRequestURI());
		}
		

     
    	/*******************************************************************
    	 * HTTPS 일 경우 만 처리
    	 ******************************************************************/ 
    	if( null != forwardedProto ) 
        {
    		if( true == request.getHeader("x-forwarded-proto").equalsIgnoreCase("https"))
    		{

    	        log.debug("======================================  x-forwarded-proto Is https");
    			return super.preHandle(request, response, handler);
    		}
        }
 
       log.debug("======================================  x-forwarded-proto Is Not https Redirect Error Page");
	 //  response.sendRedirect("/DefaultErrorPage.jsp");
     //  return false;
     
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================           END1          ======================================\n");
		}
	}
	
   
}