package com.i2max.bootweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.web.bind.annotation.*;
 

import org.springframework.stereotype.*;
 
 @SpringBootApplication 
@ComponentScan(basePackages = "com.i2max.bootweb") 
public class DemoApplication { 

	private final Logger logger = LoggerFactory.getLogger(this.getClass());



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
    }
     
}