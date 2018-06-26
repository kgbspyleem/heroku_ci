 
package com.i2max.bootweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 

import com.i2max.security.CanvasAuthorizationFilter;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);  
    
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
		 logger.info("============================================== WebSecurityConfig configure");

		    http.authorizeRequests()
            .antMatchers("/",
                    "/favicon.ico",
                    "/404.html",
                    "/403.html",
                    "/500.html",
                    "/sfdcauth/**",
                    "/login",
                    "/logout",
                    "/password",
                    "/images/**",
                    "/css/**",
                    "/fonts/**",
                    "/icons/**")
            .permitAll()
            /* Require all others to be authenticated */
            .anyRequest().authenticated()
            .and()
            /* Add the filter that turns JWT into authentication */
            .addFilter(new CanvasAuthorizationFilter(this.authenticationManager()))
            /*
             * allow direct access to the POST form for Canvas use without a
             * _csrd token
             */
            .csrf()
            .ignoringAntMatchers("/sfdcauth/**");
       
    }
}
