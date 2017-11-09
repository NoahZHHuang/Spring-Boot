package com.noah.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.web.filter.GenericFilterBean;

import com.noah.config.DynamicDataSourceHolder;

@WebFilter
public class ClearDataSourceHolderFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//It is very important to clear the DataSource for each http request.
		//because Tomcat host many threads in a pool to handle the http requests 
		//which means, the thread will not die after the request, 
		//it may bring rubbish in thread local
		//if we clear it manually it looks ugry and if we forget, it will cause issue
		//filter is a good place for it.
		DynamicDataSourceHolder.clearDataSource();
		chain.doFilter(request, response);
	}

	
	
}
