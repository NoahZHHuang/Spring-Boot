package com.noah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.noah.condition.LinuxCondition;
import com.noah.condition.WindowsCondition;
import com.noah.service.ConditionService;
import com.noah.service.LinuxConditionService;
import com.noah.service.WindowsConditionService;

@Configuration
public class ConditionConfig {

	@Bean
	@Conditional(LinuxCondition.class)
	public ConditionService linuxConditionService (){
		return new LinuxConditionService();
	}
	
	@Bean
	@Conditional(WindowsCondition.class)
	public ConditionService windowsConditionService(){
		return new WindowsConditionService();
	}
	
}
