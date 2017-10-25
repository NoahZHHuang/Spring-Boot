package com.noah.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class Bean_C {
	
	@Autowired
	private Bean_A beanA;
	
	@Autowired
	private Bean_B beanB;
	
	public void serve(){
		String msg = "Bean_C is serving you, and Bean C is calling Bean A & B";
		System.out.println(msg);
		beanA.serve();
		beanB.serve();
	}

}
