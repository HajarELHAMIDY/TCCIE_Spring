package com.example.demo.payement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MVCProperties extends WebMvcConfigurerAdapter {
	 @Override 
	    public void addCorsMappings ( CorsRegistry register) { 
	        register.addMapping ("/ **"). 
	        allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD" , "OPTIONS"); 
	    } 

}
