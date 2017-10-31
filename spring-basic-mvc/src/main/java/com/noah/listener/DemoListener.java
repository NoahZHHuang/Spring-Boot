package com.noah.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Servlet Context Initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Servlet Context Destroyed");		
	}

	
	
}
