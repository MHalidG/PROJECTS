package hh.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import hh.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	
	@NotNull(message="Kullanici Adi Bos Birakilamaz")
	@Size(min=16,max=32,message="Your Password '${validatedValue}' must be between {min} and {max} chars long")
	private String userName;
	

	//TO DO: Email unique kontrolu yapicaz.
	@NotNull(message="Email Bos Birakilamaz")
	private Email email;
	

	@NotNull(message="Sifre Bos Birakilamaz")
	@Size(min=16,max=32,message="Your Password '${validatedValue}' must be between {min} and {max} chars long")
	private String password;
	

	//TO DO: Phone Number unique kontrolu yapicaz.
	
	@Pattern(regexp= "^((\\(\\d{3}\\))|\\d{3})[-.]?\\d{3}[-/]?\\d{4}$",
			message= "Lutfen Gecerli Bir Telefon Numarasi Giriniz")
	private String phoneNumber;


	
}
