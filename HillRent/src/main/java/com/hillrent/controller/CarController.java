package com.hillrent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

	/*
	 * { "model":"BMW", "doors":4, "seats":4, "luggage":450,
	 * "transmission":"triptonic", "airConditioning":true, "age":1,
	 * "pricePerHour":250, "fuelType":"electricity" }
	 */
	// http://localhost:8080/car/admin/cbf6ce7c-1745-4207-90db-bfa1636071b2/add
	@PostMapping("/admin/{imageId}/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HRResponse> saveCar(@PathVariable String imageId, @Valid @RequestBody CarDTO carDTO) {
		carService.saveCar(carDTO, imageId);

		HRResponse response = new HRResponse();
		response.setMessage("Car successfully saved");
		response.setSuccess(true);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/visitors/all")
	public ResponseEntity<List<CarDTO>> getAllCars(){
		List<CarDTO> carDTOs=carService.getAllCars();
		return ResponseEntity.ok(carDTOs);
	
	}
	
	@GetMapping("/visitors/{id}")
	public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
		CarDTO carDTOs=carService.findById(id);
		return ResponseEntity.ok(carDTOs);
	
	}
	
	
}
