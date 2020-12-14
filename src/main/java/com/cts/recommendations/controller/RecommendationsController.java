package com.cts.recommendations.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class RecommendationsController {

	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello() {
		return "Hello world!";
	};
}
