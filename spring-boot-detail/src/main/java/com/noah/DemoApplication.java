package com.noah;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@SpringBootApplication
public class DemoApplication {
	
	@Resource
	private Environment env;
	
	private static final Logger  LOGGER =  LoggerFactory.getLogger(DemoApplication.class);
	
	@RequestMapping("/configs")
	private String getConfigs(){
		String configs = "";
		configs += ("db.url:"+env.getProperty("db.url")+"\n");
		configs += ("db.username:"+env.getProperty("db.username")+"\n");
		configs += ("db.password:"+env.getProperty("db.password")+"\n");
		LOGGER.info("getting configs: \n"+configs);
		return configs;
	}
	
	public static void main(String [] args){
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
