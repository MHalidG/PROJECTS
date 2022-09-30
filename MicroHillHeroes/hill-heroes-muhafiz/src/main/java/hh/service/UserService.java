package hh.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hh.domain.Role;
import hh.domain.User;
import hh.dto.RegisterRequest;
import hh.enums.RoleType;
import hh.exceptions.ConflictException;
import hh.exceptions.ResourceNotFoundException;
import hh.exceptions.UserExceptions;
import hh.repository.RoleRepository;
import hh.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public void register(RegisterRequest registerRequest) {
			if(userRepository.existsByUserName(registerRequest.getUserName())) {
				throw new ConflictException(String.format(UserExceptions.USER_NAME_AlREADY_EXIST,registerRequest.getUserName()));
			}
			
			String encodedPassword=passwordEncoder.encode(registerRequest.getPassword());
			
			Role role=roleRepository.findByName(RoleType.Player).
					orElseThrow(()-> new ResourceNotFoundException(String.format(UserExceptions.RESOURCE_NOT_FOUND_MESSAGE, RoleType.Player.name())));
			
			Set<Role> roles=new HashSet<>();
			roles.add(role);
			
			User user=new User();
			user.setEmail(registerRequest.getEmail());
			user.setPhoneNumber(registerRequest.getPhoneNumber());
			user.setPassword(encodedPassword);
			user.setRoles(roles);
			user.setUserName(registerRequest.getUserName());
			
			userRepository.save(user);
	}
	
	
	
}
