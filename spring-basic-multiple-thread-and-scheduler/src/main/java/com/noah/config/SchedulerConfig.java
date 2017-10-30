package com.noah.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.noah.services")
@EnableScheduling
public class SchedulerConfig {
	
}
