package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garage")
public class GarageController {

	@GetMapping("hello")
	public String hello() {
		return "Hello for garage";
	}
}
