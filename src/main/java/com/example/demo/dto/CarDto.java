package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {

	private Long id;
	private String name;
	private String colour;
	private String make;
	private String model;
	private int doors;

	// this will spit out JSON

}
