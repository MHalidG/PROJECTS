package com.tpe.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tpe.controller.request.CarRequest;
import com.tpe.domain.Car;
import com.tpe.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {

	
	private CarRepository carRepository;
	private ModelMapper modelMapper;
	
	public void saveCar(CarRequest carRequest) {
	Car car=modelMapper.map(carRequest, Car.class);
	carRepository.save(car);
			
	}
	
	
}
