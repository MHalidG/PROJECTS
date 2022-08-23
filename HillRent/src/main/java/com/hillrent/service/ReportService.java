package com.hillrent.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hillrent.domain.User;
import com.hillrent.helper.ExcelReportHelper;
import com.hillrent.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {

	UserRepository userRepository;
	
	
	public ByteArrayInputStream getUserReport() throws IOException {
		List<User> users=userRepository.findAll();
		return ExcelReportHelper.getUsersExcelReport(users);
	}
	
	
	
}
