package com.hillrent.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hillrent.domain.Car;
import com.hillrent.domain.Reservation;
import com.hillrent.domain.User;
import com.hillrent.helper.ExcelReportHelper;
import com.hillrent.repository.CarRepository;
import com.hillrent.repository.ReservationRepository;
import com.hillrent.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {

	UserRepository userRepository;
	
	CarRepository carRepository;
	
	ReservationRepository reservationRepository;
	
	public ByteArrayInputStream getUserReport() throws IOException {
		List<User> users=userRepository.findAll();
		return ExcelReportHelper.getUsersExcelReport(users);
	}
	
	
	public ByteArrayInputStream getCarReport() throws IOException {
		List<Car> cars=carRepository.findAll();
		return ExcelReportHelper.getCarsExcelReport(cars);
	}
	
	public ByteArrayInputStream getReservationReport() throws IOException {
		List<Reservation> reservations=reservationRepository.findAll();
		return ExcelReportHelper.getReservationsExcelReport(reservations);
	}
	
}
