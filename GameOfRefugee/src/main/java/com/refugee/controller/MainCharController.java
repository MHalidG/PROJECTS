package com.refugee.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refugee.domain.MainCharacter;
import com.refugee.response.RGResponse;
import com.refugee.service.MainCharService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RequestMapping("/mainchar")
@RestController
public class MainCharController {

	private MainCharService charService;
	
	@PostMapping("/add")
	public ResponseEntity<RGResponse> saveChar(@RequestBody MainCharacter mainChar) {
		charService.saveChar(mainChar);

		RGResponse response = new RGResponse();
		response.setMessage("Character Succesfully Created");
		response.setSuccess(true);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	
}
