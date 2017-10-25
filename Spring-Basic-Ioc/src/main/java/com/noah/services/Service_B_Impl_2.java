package com.noah.services;

import org.springframework.stereotype.Service;

@Service("second_service_b")
public class Service_B_Impl_2 implements Service_B {

	public void serve() {
		String msg = "Service_B(the second) is serving you!";
		System.out.println(msg);
	}

}
