package com.hillrent.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hillrent.domain.Role;
import com.hillrent.domain.User;
import com.hillrent.domain.enums.RoleType;
import com.hillrent.dto.UserDTO;
import com.hillrent.dto.mapper.UserMapper;
import com.hillrent.dto.request.RegisterRequest;
import com.hillrent.dto.request.UpdatePasswordRequest;
import com.hillrent.dto.request.UserUpdateRequest;
import com.hillrent.exception.BadRequestException;
import com.hillrent.exception.ConflictException;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.RoleRepository;
import com.hillrent.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private UserMapper userMapper;
	
	public void register(RegisterRequest registerRequest) {
	if(userRepository.existsByEmail(registerRequest.getEmail())) {
		throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST,registerRequest.getEmail()));
	}
	
	String encodedPassword=passwordEncoder.encode(registerRequest.getPassword());
	Role role=roleRepository.findByName(RoleType.ROLE_CUSTOMER).orElseThrow(()->new 
			ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE,RoleType.ROLE_CUSTOMER.name())));
	
	Set<Role> roles=new HashSet<>();
	roles.add(role); 
	
	User user=new User();
	user.setFirstName(registerRequest.getFirstName());
	user.setLastName(registerRequest.getLastName());
	user.setEmail(registerRequest.getEmail());
	user.setPassword(encodedPassword);
	user.setPhoneNumber(registerRequest.getPhoneNumber());
	user.setAddres(registerRequest.getAddress());
	user.setZipCode(registerRequest.getZipCode());
	user.setRoles(roles);
	
	userRepository.save(user);
	}
	//Entitylerin controller tarafina cikmamasi en temizi. Servicelerin icinde kalmasi en mantiklisi.
	
	
	public List<UserDTO> getAllUsers(){
		List<User> users=userRepository.findAll();
		return userMapper.map(users);		
	}

	public Page<UserDTO> getUserPage(Pageable pageable){
		Page<User> users=userRepository.findAll(pageable);
		Page<UserDTO> dtoPage=users.map(new Function<User, UserDTO>(){
		
		@Override
		public UserDTO apply(User user) {
			return userMapper.userToUserDTO(user);
		}
	});
	return dtoPage;
	}

	public UserDTO findById(Long id) {
		User user=userRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
        
        return userMapper.userToUserDTO(user);
    }


	public void updatePassword(Long id, UpdatePasswordRequest passwordRequest) {
		Optional<User> userOpt=userRepository.findById(id);
		User user=userOpt.get();
		
		
		if(user.getBuiltIn()) {
			throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
		}
	
		if(!BCrypt.hashpw(passwordRequest.getOldPassword(), 
				user.getPassword()).equals(user.getPassword())) {
			throw new BadRequestException(ErrorMessage.PASSWORD_NOT_MATCHED);
//Yukarda If'in sartinda yaptigimiz islem hashlenmis iki girdinin birbirinin aynisi olup olmadigini onayliyor
		}
	String hashedPassword=passwordEncoder.encode(passwordRequest.getNewPassword());
		user.setPassword(hashedPassword);
		userRepository.save(user);
	}

/*
	public void updateUser(Long id, @Valid UserUpdateRequest userUpdateRequest) {
		//TO DO:
		
	}
	*/
}
