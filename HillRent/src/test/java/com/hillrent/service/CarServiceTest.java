package com.hillrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hillrent.domain.Car;
import com.hillrent.dto.CarDTO;
import com.hillrent.dto.mapper.CarMapper;
import com.hillrent.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

	@Mock
	CarRepository carRepository;
	
	
	@Mock
	CarMapper carMapper;
		
	
	@InjectMocks
	CarService underTest;
	
	@Test
	public void getAllCarTest() {
		List<Car> list=new ArrayList<>();
		
		Car car=new Car();
		car.setId(1L);
		car.setModel("Merso");

		Car car1=new Car();
		car1.setId(2L);
		car1.setModel("BMW");
		
		Car car2=new Car();
		car2.setId(3L);
		car2.setModel("AUDI");
		list.add(car);
		list.add(car1);
		list.add(car2);
		
List<CarDTO> listDTO=new ArrayList<>();
		
		CarDTO carDTO=new CarDTO();
		carDTO.setId(1L);
		carDTO.setModel("Merso");

		CarDTO carDTO1=new CarDTO();
		carDTO1.setId(2L);
		carDTO1.setModel("BMW");
		
		CarDTO carDTO2=new CarDTO();
		carDTO2.setId(3L);
		carDTO2.setModel("AUDI");
		
		listDTO.add(carDTO);
		listDTO.add(carDTO1);
		listDTO.add(carDTO2);
		
		when(carRepository.findAll()).thenReturn(list);
		when(carMapper.map(list)).thenReturn(listDTO);
		
		List<CarDTO> listActual=underTest.getAllCars();
		
		assertEquals(3, listActual.size());
		
		verify(carRepository,times(1)).findAll();
	}
	
	
	
}
