package hh.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.dto.request.RegisterRequest;
import hh.lib.response.HHResponse;
import hh.lib.response.ResponseMessages;
import hh.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJwtController {

	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<HHResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
		
		userService.register(registerRequest);
		
		HHResponse response=new HHResponse();
		response.setMessage(ResponseMessages.REGISTER_RESPONSE_MESSAGE);
		response.setSuccess(true);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<T>
	
	
}
