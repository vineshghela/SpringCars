package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CarDto;
import com.example.demo.service.CarService;

@RestController
@CrossOrigin
@RequestMapping("/car") // this is to further define the path
public class CarController {

	private CarService service;

	@Autowired
	public CarController(CarService service) {
		super();
		this.service = service;
	}

	@GetMapping("/hello") // This is the mapping i want - Get me something
	public String hello() {
		return "hello from car";
	}

	// pathVariable - this is the values in the URL "?id=2"
	// @RequestBody -- put/post - POST MEHTOD body of that request

	// pattern matching here url - /hello/<valuehere>
	@GetMapping("/hello/{id}")
	public String helloName(@PathVariable String id) {
		return "Hello " + id;
	}

	// @GetMapping("/num1/{num1}/num2/{num2}/num3/{num3}")
	@GetMapping("/num1/{num1}/num2/{num2}")
	public int addTwo(@PathVariable("num1") int number1, @PathVariable("num2") int number2) {
		return number1 + number2;
	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<CarDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<CarDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

}
