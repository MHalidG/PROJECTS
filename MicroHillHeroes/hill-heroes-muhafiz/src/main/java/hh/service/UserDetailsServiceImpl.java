package hh.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.domain.User;
import hh.exceptions.UserExceptions;
import hh.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByUserName(username).orElseThrow(()->new 
				UsernameNotFoundException(String.format(UserExceptions.USER_NOT_FOUND_MESSAGE, username)));
		
		return UserDetailsImpl.build(user);
	}

}
