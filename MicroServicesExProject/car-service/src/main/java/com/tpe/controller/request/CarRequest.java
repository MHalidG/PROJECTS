package com.tpe.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

	
	private Long id;
	
	private String brand;
	
	private String model;
	
	private Integer doors;

	private Double pricePerHour;
	
	private Integer age;



}
