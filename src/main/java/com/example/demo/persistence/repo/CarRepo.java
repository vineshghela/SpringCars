package com.example.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

	// it allows us to implement
	// create
	// read
	// update
	// delete

	// custom sql statements e.g. find by make or model .........

	// find all by name
	// JPQL
	@Query(value = "SELECT * FROM CAR WHERE NAME =?1", nativeQuery = true)
	List<Car> findByName(String name);

}
