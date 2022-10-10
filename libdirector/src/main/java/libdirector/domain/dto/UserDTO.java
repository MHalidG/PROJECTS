/*package libdirector.domain.dto;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "tbl_users")
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=2,max=30,message="Your Firstname '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message="Please provide your firstname")
	@Column(length = 30,nullable=false)
	private String firstName;
	
	@Size(min=2,max=30,message="Your Lastname '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message="Please provide your lastname")
	@Column(length = 30,nullable=false)
	private String lastName;
	
	@Size(min=-2,max=2,message="Score '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message="Please provide score")
	@Column(nullable=false)
	private int score=0;
	
	@Size(min=10,max=100,message="Address '${validatedValue}' must be between {min} and {max} chars long")
	@NotNull(message="Please provide your address")
	@Column(length = 100,nullable=false)
	private String address;
	
	@NotNull(message="Please provide your name")
	@Column(nullable=false)
	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
	private String phone;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date birthDate;


	@Size(min=10,max=80)
	@Column(nullable = false/*,unique = true)
	@Email(message="Provide valid email")
	private String email;
	
	@Column(nullable=false)
	//TO DO:Hashed password
	private String password;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone= "Turkey")
	@Column(nullable=false)
	private LocalDateTime createDate;
	
	@Column
	//TO DO:Hashed password
	private String resetPasswordCode;
	
	@Column(nullable=false)
	private Boolean builtIn=false;
	
	
}*/