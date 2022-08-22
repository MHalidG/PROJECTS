package com.hillrent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hillrent.dto.ReservationDTO;
import com.hillrent.dto.request.ReservationRequest;
import com.hillrent.dto.response.HRResponse;
import com.hillrent.dto.response.ResponseMessage;
import com.hillrent.service.ReservationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private ReservationService reservationService;

	   
	
	//http://localhost:8080/reservations/add?carId=2
	@PostMapping("/add")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public ResponseEntity<HRResponse> makeReservation(HttpServletRequest request,@RequestParam(value="carId") Long carId,
			@Valid @RequestBody ReservationRequest reservationRequest){
		Long user=(Long) request.getAttribute("id");
		reservationService.createReservation(reservationRequest, user, carId);
		
		HRResponse response=new HRResponse();
		response.setMessage(ResponseMessage.RESERVATION_SAVED_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/all")
	@PreAuthorize("hasRole('ADMIN')")
	public 	ResponseEntity<List<ReservationDTO>> getAllReservations(){
		List<ReservationDTO> reservations=reservationService.getAllReservations();
		return ResponseEntity.ok(reservations);
	}
	
	
	
}
