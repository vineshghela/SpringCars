package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.CarDto;
import com.example.demo.persistence.domain.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
// sql runs in order schema followed by data file - JH dont make the mistake
@Sql(scripts = { "classpath:car-schema.sql",
		"classpath:car-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class CarControllerintergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private CarDto mapToDTO(Car car) {
		return this.mapper.map(car, CarDto.class);
	}

	// During our test we want give it some data to use
	private final Car TEST_CAR_1 = new Car(1L, "Vinesh", "Blue", "Audi", "A5", 3);
	private final Car TEST_CAR_2 = new Car(2L, "Jordan", "Blue", "BMW", "M3", 5);
	private final Car Test_car_3 = new Car(3L, "Alan", "Blue", "Honda", "Accord", 5);
	private final Car Test_car_4 = new Car(4L, "Piers", "Pink", "Honda", "Accord", 3);

	// I also want to create a list of cars that i can use later
	private final List<Car> LISTOFCARS = List.of(TEST_CAR_1, TEST_CAR_2, Test_car_3, Test_car_4);

	private final String URI = "/car";

	// Create test
	@Test
	void createTest() throws Exception {
		CarDto testDTO = mapToDTO(new Car("nick", "white", "fiat", "punto", 5));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		CarDto testSavedDTO = mapToDTO(new Car("nick", "white", "fiat", "punto", 5));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}

}
