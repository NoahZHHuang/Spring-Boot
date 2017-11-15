package com.noah.config.security;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter{

	//Here need to inject an authenticationManager
	public PreAuthenticatedProcessingFilter(AuthenticationManager authenticationManager) {
		setAuthenticationManager(authenticationManager);
	}
	
	//Principal(namely the cookie) here will be used later for authentication 
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		Cookie [] cookies = request.getCookies(); 
		if(cookies == null || cookies.length ==0){
			return null;
		}
		Optional<Cookie> token = Arrays.stream(cookies).filter(cookie-> cookie.getName().equals("TOKEN")).findFirst();
		return token.isPresent() ? token.get().getValue() : null;
		
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		//Notice!!!!!!!!!!! 
		//getPreAuthenticatedCredentials can not return a null , need to return a dummy value like 'N/A'
		//below is what Spring says in AbstractPreAuthenticatedProcessingFilter
		/*
	 		Override to extract the credentials (if applicable) from the current request.
	 		Should not return null for a valid principal, though some implementations may
	 		return a dummy value.
	    */
		//Very tricky!!
		return "N/A";
	}

}
