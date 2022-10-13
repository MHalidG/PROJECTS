package libdirector.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import libdirector.domain.User;
import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserService {
	
	
	 UserRepository userRepository;
	 public void saveUser() throws ParseException {
      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
			Date tarih = df.parse("1984/01/01");
		
  

	        User user = new User();

	        user.setFirstName("Ali");
	        user.setLastName("Gel");
	        user.setScore (0);
	        user.setAddress("Adres Adres dfsd");
	        user.setPhone("234232342");
	        user.setBirthDate(tarih);
	        user.setEmail("test3@test.com");
	        user.setPassword("1234a");
	        user.setCreateDate(LocalDateTime.now());
	        user.setResetPasswordCode("resetpass");
	        // user.setBuiltIn(false);
	        // user.setUserBooks(loans);
	        // user.setRole(roles);

	        userRepository.save(user);
}

}
