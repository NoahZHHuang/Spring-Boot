package com.noah.aspectj;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodService {

	public void serve(){
		System.out.println("DemoMethodService is serving you...");
	}
	
}
