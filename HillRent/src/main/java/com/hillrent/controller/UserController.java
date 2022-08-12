package com.hillrent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hillrent.dto.UserDTO;
import com.hillrent.dto.request.UpdatePasswordRequest;
import com.hillrent.dto.request.UserUpdateRequest;
import com.hillrent.dto.response.HRResponse;
import com.hillrent.dto.response.ResponseMessage;
import com.hillrent.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping("/auth/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return ResponseEntity.ok(users);

	}

//sistemdeki kayitli herhangi bir kullanici kendi bilgilerini getiriyor
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<UserDTO> getUserById(HttpServletRequest request) {
		Long id = (Long) request.getAttribute("id");
		UserDTO userDTO = userService.findById(id);

		return ResponseEntity.ok(userDTO);
	}

	@GetMapping("/auth/pages")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<UserDTO>> getAllUserByPage(@RequestParam("page") int page,
			@RequestParam("size") int size, @RequestParam("sort") String prop,
			@RequestParam("direction") Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
		Page<UserDTO> userDTOPage = userService.getUserPage(pageable);

		return ResponseEntity.ok(userDTOPage);
		// http://localhost:8080/user/auth/pages?size=3&page=0&sort=id&direction=DESC
		// Postman tarafinda bu sekilde arama yaparsak DESC= desending order
		// misal
		// http://localhost:8080/user/auth/pages?size=3&page=0&sort=firstName&direction=DESC
		// First Name'e gore siraliyor

	}

	// http://localhost:8080/user/2/auth
	// to get any user in the system, Admin is able to use this method
	@GetMapping("/{id}/auth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUserByIdAdmin(@PathVariable Long id) {
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	// patch mapping ile put mapping arasinda ne fark var?
	// putmapping de db ye bakiyor update edilecek hesap varmi varsa update yoksa
	// create

	// patch mapping once kontrol ediliyor var mi yok mu diye update edilecek nesne
	// dogruluyor sonra

	@PatchMapping("/auth")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<HRResponse> updatePassword(HttpServletRequest httpServletRequest,
			@RequestBody UpdatePasswordRequest passwordRequest) {
		Long id = (Long) httpServletRequest.getAttribute("id");
		userService.updatePassword(id, passwordRequest);

		HRResponse response = new HRResponse();
		response.setMessage(ResponseMessage.PASSWORD_CHANGED_MESSAGE);
		response.setSuccess(true);

		/*
		 * 
		 * Thread thread=new Thread(new Runnable(){ public void run() {
		 * 
		 * } });
		 * 
		 * Thread olusturmak single argument alip single value donuyor
		 * 
		 * 
		 */

		return ResponseEntity.ok(response);

	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('CUSTOMER')")
	public ResponseEntity<HRResponse> updateUser(HttpServletRequest httpServletRequest,@Valid @RequestBody UserUpdateRequest userUpdateRequest){
		Long id=(Long) httpServletRequest.getAttribute("id");
		userService.updateUser(id,userUpdateRequest);
		HRResponse response = new HRResponse();
		response.setMessage(ResponseMessage.UPDATE_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
		
		
	}
	

}
