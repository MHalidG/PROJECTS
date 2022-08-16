package com.hillrent.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillrent.dto.CarDTO;
import com.hillrent.dto.response.HRResponse;
import com.hillrent.service.CarService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

	private CarService carService;
		
	
	@PostMapping("/admin/{imageId}/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HRResponse> saveCar(@PathVariable String imageId,@Valid @RequestBody CarDTO carDTO){
		 carService.saveCar(carDTO, imageId);
		 
		 
		 HRResponse response=new HRResponse();
		 response.setMessage("Car successfully saved");
		 response.setSuccess(true);
		 
		 return new ResponseEntity<>(response,HttpStatus.CREATED);
		 
		
		
	}
	
	
}
