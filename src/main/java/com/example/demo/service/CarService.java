package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CarDto;
import com.example.demo.persistence.domain.CarDomain;
import com.example.demo.persistence.repo.CarRepo;

@Service
public class CarService {

	// this is where our business logic will happen

//	this is also where CRUD logic will take place.

	// implements are crud functionality
	private CarRepo repo;

	// makes object mapping easy by automatically determining how one object model
	// maps to another.
	private ModelMapper mapper;

	// we create our mapToDto

	private CarDto mapToDTO(CarDomain car) {
		return this.mapper.map(car, CarDto.class);
	}

	@Autowired
	public CarService(CarRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create

	// Read all

	// read all method
	public List<CarDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// read one method
	public CarDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}

}
