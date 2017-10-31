package com.noah.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//MvcConfig act like spring-mvc.xml
@Configuration
@EnableWebMvc
// all the Controller should be under com.noah
// only scan the Controller
@ComponentScan(basePackages = { "com.noah" }, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class MvcConfig {

}
