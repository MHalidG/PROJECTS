package com.hillrent.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hillrent.domain.Car;
import com.hillrent.domain.ImageFile;
import com.hillrent.dto.CarDTO;
import com.hillrent.dto.mapper.CarMapper;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.CarRepository;
import com.hillrent.repository.ImageFileRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor

@Service
public class CarService {

	private CarRepository carRepository;
	
	private ImageFileRepository imageFileRepository;
	
	private CarMapper carMapper;
	
	
	@Transactional(readOnly=true)
	public List<CarDTO> getAllCars(){
		List<Car> carList=carRepository.findAll();
		return carMapper.map(carList);
	}
	
	//http://localhost:8080/car/visitors/1
	@Transactional(readOnly=true)
	public CarDTO findById(Long id) {
		Car car=carRepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));
		return carMapper.carToCarDTO(car);
	}
	
	
	
	public void saveCar(CarDTO carDTO, String imageId) {
		ImageFile imFile=	 imageFileRepository.findById(imageId).
			orElseThrow(()->new ResourceNotFoundException
					(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, imageId)));
		
//		Car car= CarMapper.INSTANCE.carDTOToCar(carDTO);
		
		Car car= carMapper.carDTOToCar(carDTO);
		
		Set<ImageFile> imFiles=new HashSet<>();
		imFiles.add(imFile);
		car.setImage(imFiles);
		
		carRepository.save(car);
		//mapstruct ThirdParty dependencylerden birisi.
		//Performans olarak en iyi olanlardan bu dependency.
		//modelMapper da var Third Party mapperlardan birisi.
	}
	
	

}
