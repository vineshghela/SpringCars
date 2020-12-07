package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GarageDto {

	private Long id;
	private String location;
	private int maxVehicles;

}
