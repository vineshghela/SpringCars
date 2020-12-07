package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {

	private Long id;
	private String colour;
	private String make;
	private String model;
	private int doors;
}
