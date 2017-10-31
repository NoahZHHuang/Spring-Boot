package com.noah.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	public String getGreeting(String name){
		return "Hello Dear " + name + " ...";
	}
	
}
