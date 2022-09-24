package com.tpe.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tpe.CarDTO;
import com.tpe.controller.request.ReservationRequest;
import com.tpe.domain.Reservation;
import com.tpe.enums.ReservationStatus;
import com.tpe.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {

	
	private EurekaClient eurekaClient;
	
	private RestTemplate restTemplate;
	
	
	public void saveReservation(Long carId, ReservationRequest reservationRequest) {
		InstanceInfo instanceInfo=eurekaClient.getApplication("car-service").getInstances().get(0);
		String baseUrl=instanceInfo.getHomePageUrl();
		
		String path="/car/";
		
		//localhost:8082/car/1
		String servicePath=baseUrl+path+carId.toString();
		
		ResponseEntity<CarDTO> carDTOResponse=restTemplate.getForEntity(servicePath, CarDTO.class);
		
		if(!(carDTOResponse.getStatusCode()==HttpStatus.OK)) {
			throw new ResourceNotFoundException("Car not found with id:"+carId);
		}
		
		Reservation reservation=new Reservation();
		
		reservation.setCarId(carDTOResponse.getBody().getId());
		reservation.setPickUpTime(reservationRequest.getPickUpTime());
		reservation.setDropOffTime(reservationRequest.getDropOffTime());
		reservation.setPickUpLocation(reservationRequest.getPickUpLocation());
		reservation.setDropOffLocation(reservationRequest.getDropOffLocation());
		
		reservation.setStatus(ReservationStatus.CREATED);
		
		//TODO TotalPrice Set edilecek sonrada reservation save edilecek
	}
	
	
	
	
	
}
