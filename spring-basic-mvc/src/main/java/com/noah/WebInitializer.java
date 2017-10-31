package com.noah;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.noah.config.MvcConfig;
import com.noah.filter.DemoFilter;
import com.noah.filter.SpringBasedFilter;
import com.noah.listener.DemoListener;

//WebApplicationInitializer act like the "web.xml"
public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		servletContext.addFilter("demoFilter", DemoFilter.class);
		servletContext.addFilter("SpringBasedFilter", SpringBasedFilter.class);
		servletContext.addListener(DemoListener.class);
		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MvcConfig.class);
		ctx.setServletContext(servletContext);
		
		javax.servlet.ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		
		
		
	}

	
	
}
