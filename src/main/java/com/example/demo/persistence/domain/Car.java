package com.example.demo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // classes that represent tables in our DB
@Data
@NoArgsConstructor
public class Car {

	// default constructor
	// all args constructor
	// getters
	// setters
	// toString
	// equals and hasCode

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String colour;

	@NotNull // mean not empty
	private String make;

	@NotNull
	private String model;

	@NotNull
	@Min(3) // min value
	@Max(5) // max value
	private int doors;

	public Car(Long id, String name, String colour, String make, String model, int doors) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.make = make;
		this.model = model;
		this.doors = doors;
	}

	public Car(String name, String colour, String make, String model, int doors) {
		super();
		this.name = name;
		this.colour = colour;
		this.make = make;
		this.model = model;
		this.doors = doors;
	}

}
