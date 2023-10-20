package com.bcure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SwaggerController {
	
	@GetMapping("get")
	public String getMessage() {
		
		return "Swagger-ui";
	}

}
