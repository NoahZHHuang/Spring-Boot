package com.noah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noah.service.HelloService;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService helloService;

	@RequestMapping(value = "/hello/{name}", method={RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String hello(@PathVariable("name") String name) {
		return helloService.getGreeting(name);
	}

}
