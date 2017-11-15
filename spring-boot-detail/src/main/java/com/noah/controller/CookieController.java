package com.noah.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie")
public class CookieController {

	//here the token format is username_password, i.e. noah_123456
	@RequestMapping(value="/{token}",method = RequestMethod.GET)
	public void getCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable("token") String token) {
	    Cookie cookie = new Cookie("TOKEN", token);
	    cookie.setPath("/");
	    cookie.setMaxAge(10000); //expired in 100s
	    response.addCookie(cookie);
	}
	
}
