 
package com.i2max.bootweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class); 
	 public WebSecurityConfig()
	 {
		 logger.info("============================================== WebSecurityConfig Constructor");
	 }
    @Override
    protected void configure(final HttpSecurity http) throws Exception { 
        http
    	.authorizeRequests()
		.antMatchers("/**")
		.permitAll();
       
    }
}
