package com.noah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	@RequestMapping("/public")
	private String getInfo() {
		return "this is public info";
	}

}
