package com.hillrent.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hillrent.domain.Car;
import com.hillrent.dto.CarDTO;

//@Mapper(componentModel="spring")
@Mapper
public interface CarMapper {

	CarMapper INSTANCE=Mappers.getMapper(CarMapper.class);
	
	
	@Mapping(target="image",ignore=true)
	public Car carDTOToCar(CarDTO carDTO);
	
	
}
