package com.noah;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent>{

	public void onApplicationEvent(DemoEvent event) {
		
		System.out.println("DemoListener recieve the msg \"" + event.getMsg() +"\" from "+ event.getSource());
		
	}

}
