package com.i2max.bootweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.web.bind.annotation.*;
 

import org.springframework.stereotype.*;
 

@Controller 
@SpringBootApplication  
@ComponentScan(basePackages = "com.i2max.bootweb") 
public class DemoApplication { 

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping( "/test"  )
    @ResponseBody
    String home() { 
      return "Hello World!";
    }
     
    @RequestMapping("/test1")
    @ResponseBody
    String home1() { 
      return "Hello Worlds";
    } 
     

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
    }
     
}