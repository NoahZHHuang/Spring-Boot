package com.noah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@RequestMapping("/admin")
	private String getInfo() {
		return "this is admin info";
	}

}
