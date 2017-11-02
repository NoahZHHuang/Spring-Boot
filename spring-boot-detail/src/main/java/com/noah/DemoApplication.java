package com.noah;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;


@SpringBootApplication
public class DemoApplication {
	
	@Resource
	private Environment env;
	
	private static final Logger  LOGGER =  LoggerFactory.getLogger(DemoApplication.class);
	
	
	public static void main(String [] args){
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	// CommandLineRunner is used in such situation 
	// When the application start up, there is some initialization job to do, do it here
	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			LOGGER.info("DemoApplication is running ...");
			LOGGER.info("Active profile is: {}", env.getActiveProfiles()[0]); 
			LOGGER.info("DB Connection");
			LOGGER.info("DB url: {}", env.getProperty("db.connection.url"));
			LOGGER.info("DB pwd: {}", env.getProperty("db.connection.username"));
		};
		
	}
	
}
