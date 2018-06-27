package com.i2max.bootweb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class I2MAXErrorController implements ErrorController  {
	    @RequestMapping("/error")
	    public String handleError() {
	        //do something like logging
	        return "error";
	    }
	 
	    @Override
	    public String getErrorPath() {
	        return "/error";
	    }
}
