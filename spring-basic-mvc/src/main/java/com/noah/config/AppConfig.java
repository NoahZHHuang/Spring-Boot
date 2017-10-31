package com.noah.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//AppConfig act like spring-config.xml/applicationContext.xml
@Configuration
//scan all Class except the Controller
@ComponentScan(basePackages = { "com.noah" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class AppConfig {

}
