package com.noah.aspectj;

import org.springframework.stereotype.Service;

@Service
public class DemoAroundService {
	public void serve(){
		System.out.println("DemoAroundService is serving you...");
	}
}
