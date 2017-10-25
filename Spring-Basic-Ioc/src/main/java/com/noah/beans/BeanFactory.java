package com.noah.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration should be used together with @Bean
//Class with @Configuration means it is a configuration class
//Method with @Bean is telling Spring to call these methods to create singleton beans and store them in Spring Context
//Once spring start up, these beans can be @Autowired
@Configuration
public class BeanFactory {
	
	@Bean
	//@Bean can also work together with @Scope like:
	// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
	// @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) 
	// and Singleton is by default
	public Bean_A createBeanA(){
		return new Bean_A();
	}

	@Bean
	public Bean_B createBeanB(){
		return new Bean_B();
	}
	
	@Bean
	public Bean_C createBeanC(){
		return new Bean_C();
	}
	
	@Bean(name="first_bean_d")
	public Bean_D createBeanD_1(){
		return new Bean_D("first bean D");
	}
	
	@Bean(name="second_bean_d")
	public Bean_D createBeanD_2(){
		return new Bean_D("second bean D");
	}
	
	
}
