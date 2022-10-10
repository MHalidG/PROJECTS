package libdirector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;

import libdirector.domain.User;

class UserSetup {

	@Test
	void test() {
		User user1=new User();
		user1.setAddress("Boston Entertainment");
		user1.setBirthDate();
		
	}

}
