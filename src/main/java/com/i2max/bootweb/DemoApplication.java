package com.i2max.bootweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
 

import org.springframework.stereotype.*;
 

@Controller
@SpringBootApplication
public class DemoApplication { 

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    @ResponseBody
    String home() { 
      return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
    }
     
}