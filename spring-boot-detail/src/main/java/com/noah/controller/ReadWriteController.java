package com.noah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadWriteController {

	@RequestMapping("/readwrite")
	private String getInfo() {
		return "this is read write info";
	}

}
