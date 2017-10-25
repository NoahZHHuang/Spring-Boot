package com.noah.aspectj;

import org.springframework.stereotype.Service;


@Service
public class DemoAnnotationService {

	@Action(name="Jump")
	public void serve(){
		System.out.println("DemoAnnotationService is serving you...");
	}
}
