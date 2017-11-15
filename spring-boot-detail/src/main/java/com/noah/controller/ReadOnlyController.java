package com.noah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadOnlyController {

	@RequestMapping("/readonly")
	private String getInfo() {
		return "this is read only info";
	}

}
