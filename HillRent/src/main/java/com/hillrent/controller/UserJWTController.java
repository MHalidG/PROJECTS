package com.hillrent.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillrent.dto.request.LoginRequest;
import com.hillrent.dto.request.RegisterRequest;
import com.hillrent.dto.response.HRResponse;
import com.hillrent.dto.response.LoginResponse;
import com.hillrent.dto.response.ResponseMessage;
import com.hillrent.security.jwt.JwtUtils;
import com.hillrent.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

	private UserService userService;
	
	private AuthenticationManager authManager;
	
	private JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<HRResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
	userService.register(registerRequest);
	
	com.hillrent.dto.response.HRResponse response=new HRResponse();
	response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
	response.setSuccess(true);
	
	return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication=authManager.authenticate(new 
				UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
		
		String token=jwtUtils.generateJwtToken(authentication);
		
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		
		
	}
	
	
}
