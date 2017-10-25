package com.noah.services;

import org.springframework.stereotype.Service;

@Service
public class Service_A {

	public void serve(){
		String msg = "Service_A is serving you!";
		System.out.println(msg);
	}
	
	
}
