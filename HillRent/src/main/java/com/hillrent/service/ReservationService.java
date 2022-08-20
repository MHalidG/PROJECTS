package com.hillrent.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hillrent.domain.Car;
import com.hillrent.domain.enums.ReservationStatus;
import com.hillrent.dto.request.ReservationRequest;
import com.hillrent.exception.BadRequestException;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.CarRepository;
import com.hillrent.repository.ReservationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	
	private CarRepository carRepository;
	
	private CarService carService;
	
	public void createReservation(ReservationRequest reservationRequest, Long userId,Long carId) {
		
		checkReservationTimeIsCorrect(reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());
		
		Car car=carRepository.findById(carId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, carId)));
		
		
	}
	
	public boolean checkCarAvailability(Long carId, LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
		ReservationStatus [] status= {ReservationStatus.CANCELLED,ReservationStatus.DONE};
		//TODO: checkStatus metodu Repositoryde yazilacak ve burada kullanilacaki
		
		return false;
	}
	
	private void checkReservationTimeIsCorrect(LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
		LocalDateTime now=LocalDateTime.now();
		if(pickUpTime.isBefore(now)) {
			throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
		}
		
		boolean isEqual=pickUpTime.isEqual(dropOffTime)?true:false;
		boolean isBefore=pickUpTime.isBefore(dropOffTime)?true:false;
		
		if(isEqual||!isBefore) {
			throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
		}
		
	}
	
	
	
	
}
