package com.hillrent.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hillrent.domain.Car;
import com.hillrent.domain.Reservation;
import com.hillrent.domain.User;
import com.hillrent.domain.enums.ReservationStatus;
import com.hillrent.dto.mapper.ReservationMapper;
import com.hillrent.dto.request.ReservationRequest;
import com.hillrent.exception.BadRequestException;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.CarRepository;
import com.hillrent.repository.ReservationRepository;
import com.hillrent.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservationService {
	//Show view butonundan Taskleri secince ToDo lari gosteriyor projedeki
	
	
	
	private ReservationRepository reservationRepository;
	
	private CarRepository carRepository;
	
	private CarService carService;
	
	private UserRepository userRepository;
	
	private ReservationMapper reservationMapper;
	
	
	
	
	public void createReservation(ReservationRequest reservationRequest, Long userId,Long carId) {
		
		checkReservationTimeIsCorrect(reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());
		
		Car car=carRepository.findById(carId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, carId)));
		
		boolean carStatus=checkCarAvailability(carId, reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());
		
		User user=userRepository.findById(userId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));
		
		Reservation reservation=reservationMapper.reservationRequestToReservation(reservationRequest);
		
		if(!carStatus) {
			reservation.setStatus(ReservationStatus.CREATED);
		}else {
			throw new BadRequestException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
		}
		reservation.setCarId(car);
		reservation.setUserId(user);
		
		Double totalPrice = getTotalPrice(car, reservation.getPickUpTime(), reservation.getDropOffTime());
		reservation.setTotalPrice(totalPrice);
		
		reservationRepository.save(reservation);
		
	}
	
	private Double getTotalPrice(Car car,LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
		Long hours=(new Reservation()).getTotalHours(pickUpTime, dropOffTime);
	
		return car.getPricePerHour()*hours;
	}
	
	public boolean checkCarAvailability(Long carId, LocalDateTime pickUpTime,LocalDateTime dropOffTime) {
		ReservationStatus [] status= {ReservationStatus.CANCELLED,ReservationStatus.DONE};
		
		List<Reservation> existReservations=reservationRepository.checkCarStatus(carId,pickUpTime,dropOffTime,status);
		
		//return existReservations.size()>0; 
		return !existReservations.isEmpty();
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
