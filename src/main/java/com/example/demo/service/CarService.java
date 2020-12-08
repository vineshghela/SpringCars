package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CarDto;
import com.example.demo.exceptions.CarNotFoundException;
import com.example.demo.persistence.domain.Car;
import com.example.demo.persistence.repo.CarRepo;
import com.example.demo.util.SpringBeanUtil;

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

	private CarDto mapToDTO(Car car) {
		return this.mapper.map(car, CarDto.class);
	}

	@Autowired
	public CarService(CarRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public CarDto create(Car car) {
		return this.mapToDTO(this.repo.save(car));
	}

	// read all method
	public List<CarDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}

	// read one method
	public CarDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(CarNotFoundException::new));
	}

	// update
	public CarDto update(CarDto carDto, Long id) {
		// check if record exists
		Car toUpdate = this.repo.findById(id).orElseThrow(CarNotFoundException::new);
		// set the record to update
		toUpdate.setName(carDto.getName());
		// check update for any nulls
		SpringBeanUtil.mergeNotNull(carDto, toUpdate);
		// retun the method from repo
		return this.mapToDTO(this.repo.save(toUpdate));

	}

	// what happenes when you try to merge null stuff?

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);// true
		return !this.repo.existsById(id);// true
	}

	// Find by name
	public List<CarDto> findByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}

}
