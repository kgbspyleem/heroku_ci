package com.i2max.bootweb.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);  
	    @GetMapping("/sample")
	    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
	        model.addAttribute("name", name);
	        return "sample";
	    } 
	    
	    @RequestMapping("/hello")
	    public String helloJSP(Model model ) { 
	    	model.addAttribute("name", "SpringBlog from Millky");
	        return "hello";
	    } 
	   
	     @RequestMapping(value = "/mc_test/test", method = RequestMethod.GET)
		    @ResponseBody
		    public String test(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
	        	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
		        return "Hello test!";
		    }
	    
        @RequestMapping(value = "/mc_test/execute", method = RequestMethod.GET)
	    @ResponseBody
	    public String execute(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
        	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
	        return "Hello World!";
	    }
	    
	    
        @RequestMapping(value = "/mc_test/save" , method = RequestMethod.GET)
  	    @ResponseBody
  	    public String mc_save(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
          	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
  	        return "save World!";
  	    }
  	    
        @RequestMapping(value = "/mc_test/publish" , method = RequestMethod.GET)
  	    @ResponseBody
  	    public String mc_publish(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
          	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
  	        return "publish World!";
  	    }
  	    
        @RequestMapping(value = "/mc_test/validate", method = RequestMethod.GET)
  	    @ResponseBody
  	    public String mc_validate(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
          	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
  	        return "validate World!";
  	    }
  	    
        @RequestMapping(value = "/mc_test/stop", method = RequestMethod.GET)
  	    @ResponseBody
  	    public String mc_stop(@RequestParam(value="encodedJWT ", defaultValue="") String encodedJWT ,@RequestParam(value="secret", defaultValue="") String secret  ) {   
          	logger.info("encodedJWT secret =================================" + encodedJWT + "=============" + secret); 
  	        return "stop World!";
  	    }
  	    
	    
}
