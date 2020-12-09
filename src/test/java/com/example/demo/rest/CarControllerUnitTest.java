package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.CarDto;
import com.example.demo.persistence.domain.Car;
import com.example.demo.service.CarService;

@SpringBootTest // spring boot test lets spring know this is a test file and treat as such
@ActiveProfiles("dev") // lets us set the application porperties file.
public class CarControllerUnitTest {

	@Autowired
	private CarController controller;

	@MockBean
	private CarService service;

	@Autowired
	private ModelMapper mapper;

	// same thing from our service
	private CarDto maptoDto(Car car) {
		return this.mapper.map(car, CarDto.class);
	}

	// During our test we want give it some data to use
	private final Car TEST_CAR_1 = new Car(1L, "Vinesh", "Blue", "Audi", "A5", 3);
	private final Car TEST_CAR_2 = new Car(2L, "Jordan", "Blue", "BMW", "M3", 5);
	private final Car Test_car_3 = new Car(3L, "Alan", "Blue", "Honda", "Accord", 5);
	private final Car Test_car_4 = new Car(4L, "Piers", "Pink", "Honda", "Accord", 3);

	// I also want to create a list of cars that i can use later
	private final List<Car> LISTOFCARS = List.of(TEST_CAR_1, TEST_CAR_2, Test_car_3, Test_car_4);

	// Create
	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_CAR_1)).thenReturn(this.maptoDto(TEST_CAR_1));
		assertThat(new ResponseEntity<CarDto>(this.maptoDto(TEST_CAR_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_CAR_1));
		verify(this.service, atLeastOnce()).create(TEST_CAR_1);

	}

	// Read one
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_CAR_1.getId())).thenReturn(this.maptoDto(TEST_CAR_1));
		assertThat(new ResponseEntity<CarDto>(this.maptoDto(TEST_CAR_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_CAR_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_CAR_1.getId());
	}

	// Read all
	@Test
	void readAllTest() throws Exception {
		List<CarDto> dtos = LISTOFCARS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(dtos);
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();

	}

	// Update
	@Test
	void UpdateTest() throws Exception {
		when(this.service.update(this.maptoDto(TEST_CAR_1), TEST_CAR_1.getId())).thenReturn(this.maptoDto(TEST_CAR_1));
		assertThat(new ResponseEntity<CarDto>(this.maptoDto(TEST_CAR_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_CAR_1.getId(), this.maptoDto(TEST_CAR_1)));
		verify(this.service, atLeastOnce()).update(this.maptoDto(TEST_CAR_1), TEST_CAR_1.getId());
	}

	// Delete
	@Test
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_CAR_1.getId())).thenReturn(false);
		assertThat(this.controller.delete(TEST_CAR_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.service, atLeastOnce()).delete(TEST_CAR_1.getId());

	}

	@Test
	void deleteTest2() throws Exception {
		when(this.service.delete(TEST_CAR_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_CAR_1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_CAR_1.getId());

	}

	// Find by name
	@Test
	void findByName() throws Exception {
		List<CarDto> dtos = LISTOFCARS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.findByName(TEST_CAR_1.getName())).thenReturn(dtos);
		assertThat(this.controller.findByName(TEST_CAR_1.getName()))
				.isEqualTo(new ResponseEntity<List<CarDto>>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).findByName(TEST_CAR_1.getName());
	}

}
