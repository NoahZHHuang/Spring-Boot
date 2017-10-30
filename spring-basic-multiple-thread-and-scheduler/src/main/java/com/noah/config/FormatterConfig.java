package com.noah.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterConfig {
	
	@Bean
	public SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("HH:mm:ss");
	}
	
}
