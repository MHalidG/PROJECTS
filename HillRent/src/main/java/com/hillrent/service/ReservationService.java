package com.hillrent.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hillrent.domain.Car;
import com.hillrent.domain.Reservation;
import com.hillrent.domain.User;
import com.hillrent.domain.enums.ReservationStatus;
import com.hillrent.dto.ReservationDTO;
import com.hillrent.dto.mapper.ReservationMapper;
import com.hillrent.dto.request.ReservationRequest;
import com.hillrent.dto.request.ReservationUpdateRequest;
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
	
	private UserRepository userRepository;
	
	private ReservationMapper reservationMapper;
	
	
	@Transactional(readOnly=true)
	public List<ReservationDTO> getAllReservations(){
		return reservationRepository.findAllBy();
	}
	
	@Transactional(readOnly=true)
	public ReservationDTO findById(Long id) {
		return reservationRepository.findDTOById(id).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
		
	}
	
	@Transactional(readOnly = true)
	public List<ReservationDTO> findAllByUserId(Long userId){
		User user=userRepository.findById(userId).orElseThrow(()->
					new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));
		
		return reservationRepository.findAllByUserId(user);
	}
	
	
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
		
		Double totalPrice = getTotalPrice(carId, reservation.getPickUpTime(), reservation.getDropOffTime());
		reservation.setTotalPrice(totalPrice);
		
		reservationRepository.save(reservation);
		
	}
	
	public void updateReservation(Long reservationId, Long carId,ReservationUpdateRequest reservationUpdateRequest) {

		Reservation reservation=reservationRepository.findById(reservationId).orElseThrow(()->new 
		ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, carId)));
		
		checkReservationTimeIsCorrect(reservationUpdateRequest.getPickUpTime(), reservationUpdateRequest.getDropOffTime());
		
		Car car=carRepository.findById(carId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, carId)));
		
		boolean carStatus=checkCarAvailability(carId, reservationUpdateRequest.getPickUpTime(), reservationUpdateRequest.getDropOffTime());
		
		if(reservationUpdateRequest.getPickUpTime().compareTo(reservation.getPickUpTime())==0&&
			reservationUpdateRequest.getDropOffTime().compareTo(reservation.getDropOffTime())==0&&
			car.getId().equals(reservation.getCarId().getId())){
				
				reservation.setStatus(reservationUpdateRequest.getStatus());
			}else if(carStatus) {
				throw new BadRequestException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
			}
		
		Double totalPrice=getTotalPrice(carId,reservationUpdateRequest.getPickUpTime(),reservationUpdateRequest.getDropOffTime());
		reservation.setTotalPrice(totalPrice);
		reservation.setCarId(car);
		reservation.setPickUpTime(reservationUpdateRequest.getPickUpTime());
		 reservation.setDropOffTime(reservationUpdateRequest.getDropOffTime());
		 reservation.setPickUpLocation(reservationUpdateRequest.getPickUpLocation());
		 reservation.setDropOffLocation(reservationUpdateRequest.getDropOffLocation());
		 
		 reservationRepository.save(reservation);
	}
	
	
public Double getTotalPrice(Long carId, LocalDateTime pickUpTime, LocalDateTime dropffTime) {
		
		Car car= carRepository.findById(carId).orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, carId)));
		
		Long hours= (new Reservation()).getTotalHours(pickUpTime, dropffTime);
		
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

	public void removeById(Long id) {
		boolean exist=reservationRepository.existsById(id);
		if(!exist) {
			throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id));
		}
		reservationRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public ReservationDTO findByIdAndUserId(Long id, Long userId) {
		User user=userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));
		 
		return reservationRepository.findByIdAndUserId(id, user).orElseThrow(()->new 
				 ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));
		 
	}
	
	
}
