package com.noah.cglib;

import org.junit.Test;

public class CglibTest {

	
	@Test
	public void testCglibProxy(){
		
		Greeting greetingWithProxy = (Greeting)CglibProxy.getInstance().getProxy(Greeting.class);
		greetingWithProxy.sayHello("Jack");
		
		Greeting greeting = new Greeting();
		greeting.sayHello("Jack");
		
		//compare the difference between "greetingWithProxy" and "greeting"
	}

}
