package com.tpe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tpe.CarDTO;
import com.tpe.ReservationDTO;
import com.tpe.controller.request.ReservationRequest;
import com.tpe.domain.Reservation;
import com.tpe.enums.ReservationStatus;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.ReservationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {

	
	private EurekaClient eurekaClient;
	
	private RestTemplate restTemplate;
	
	private ReservationRepository reservationRepository;
	
	private ModelMapper modelMapper;
	
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
		
		CarDTO carDTO=carDTOResponse.getBody();
		
		Reservation reservation=new Reservation();
		
		reservation.setCarId(carDTO.getId());
		
		reservation.setPickUpTime(reservationRequest.getPickUpTime());
		reservation.setDropOffTime(reservationRequest.getDropOffTime());
		reservation.setPickUpLocation(reservationRequest.getPickUpLocation());
		reservation.setDropOffLocation(reservationRequest.getDropOffLocation());
		
		reservation.setStatus(ReservationStatus.CREATED);
		
		double tp=totalPrice(reservationRequest.getPickUpTime(),reservationRequest.getDropOffTime(),carDTO);
		
		reservation.setTotalPrice(tp);
		
		reservationRepository.save(reservation);
	
	
	}
	
	public List<ReservationDTO> getAllReservation(){
		List<Reservation> rList=reservationRepository.findAll();
		
		List<ReservationDTO> rDTOList=rList.stream().map(this::mapReservationToDTO).collect(Collectors.toList());
	return rDTOList;
	
	}
	
	private ReservationDTO mapReservationToDTO(Reservation reservation) {
		ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);//map methodunun ilk parametresi kaynak field
		//Ikinci parametresi de cevirme isleminin yapilacagi hedef class
		return reservationDTO;
	}
	
	
	
	private Double totalPrice(LocalDateTime pickUpTime, LocalDateTime dropOffTime,CarDTO carDTO) {
		Long hours=(new Reservation()).getTotalHours(pickUpTime,dropOffTime);
		return carDTO.getPricePerHour()*hours;
	}
	
	
	
}
