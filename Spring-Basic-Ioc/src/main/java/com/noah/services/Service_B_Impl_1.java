package com.noah.services;

import org.springframework.stereotype.Service;

//@Service is just like @Bean,  telling Spring to create the instance and store into Spring Context
//but @Service is Class level, @Bean is method level, and @Bean has to work with @Configuration
//and please notice, @Bean(name="distinguisher1") &  @Service("distinguisher2") , the distinguisher need to be different, otherwise it will impact each other, because Spring Context is a Map
//There are some other IoC annotation are like @Serive, i.e. @Controller(means it is a web controller) & @Repository(means it is a DAO)
@Service("first_service_b")
public class Service_B_Impl_1 implements Service_B{

	public void serve() {
		String msg = "Service_B(the first) is serving you!";
		System.out.println(msg);
	}
	
}
