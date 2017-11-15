package com.noah.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noah.config.security.Role;

@RestController
public class AnotherAdminController {
	//Even in the WebSecurityConfig, no matchers for  path "/another_admin"
	//we can also use the annotaion @Secure to control the authentication
	@Secured(value = { Role.ADMIN })
	@RequestMapping("/another_admin")
	private String getInfo() {
		return "this is another admin info";
	}

}
