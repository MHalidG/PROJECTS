package com.hillrent.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hillrent.domain.Car;
import com.hillrent.domain.ImageFile;
import com.hillrent.dto.CarDTO;
import com.hillrent.dto.mapper.CarMapper;
import com.hillrent.exception.BadRequestException;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.CarRepository;
import com.hillrent.repository.ImageFileRepository;
import com.hillrent.repository.ReservationRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor

@Service
public class CarService {

	private CarRepository carRepository;
	
	private ImageFileRepository imageFileRepository;
	
	private CarMapper carMapper;
	
	private ReservationRepository reservationRepository;
	
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
	
	@Transactional(readOnly=true)
	public Page<CarDTO> findAllWithPage(Pageable pageable){
		return carRepository.findAllCarWithPage(pageable);
	}
	
/*
 
 *This method is used to update a ca
 @param id	 		Car id of the car that will be updated
 @param imageId		This is image id
 @param carDTO		This is carDTO to keep data about the car
 
 */
	
	@Transactional
	public void updateCar(Long id, String imageId,CarDTO carDTO) {
		Car foundCar=carRepository.findById(id).orElseThrow(()->new ResourceNotFoundException
				(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		ImageFile imFile=imageFileRepository.findById(imageId).orElseThrow(()->new ResourceNotFoundException
				(String.format(ErrorMessage.IMAGE_NOT_FOUND_MESSAGE, imageId)));
		
		if (foundCar.getBuiltIn()) {
			throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
		}
		Set<ImageFile> imgs=foundCar.getImage();
		imgs.add(imFile);
		
		Car car=carMapper.carDTOToCar(carDTO);
	
		car.setId(foundCar.getId());
		car.setImage(imgs);
		carRepository.save(car);
	
	
	}
	public void deleteById(Long id) {
		Car car=carRepository.findById(id).orElseThrow(()->new ResourceNotFoundException
				(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
		boolean exist=reservationRepository.existsByCarId(car);
		
		if(exist) {
			throw new BadRequestException(ErrorMessage.CAR_USED_BY_RESERVATION_MESSAGE);
		}
		
		if(car.getBuiltIn()) {
			throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
		}
		carRepository.deleteById(id);
	}
	
	
	
}
