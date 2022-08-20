package com.hillrent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillrent.service.ReservationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private ReservationService reservationService;

	
	
}
