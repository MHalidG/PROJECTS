package com.tpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.ReservationDTO;
import com.tpe.controller.request.ReservationRequest;
import com.tpe.service.ReservationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

	private ReservationService reservationService;
	

	
	@PostMapping("/{carId}")
	public ResponseEntity<Map<String,String>> 
				saveReservation(@PathVariable Long carId,@RequestBody ReservationRequest reservationRequest){
		
	reservationService.saveReservation(carId, reservationRequest);
	
	Map<String,String>map=new HashMap<>();
	map.put("message", "Reservation Succesfully Created");
	map.put("success","true");
	
	return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<ReservationDTO>> getAllReservation(){
		List<ReservationDTO> allReservations=reservationService.getAllReservation();	
	return ResponseEntity.ok(allReservations);
	
	}
	
}
