package libdirector.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @Size(min = 2, max = 30, message = "Your first name '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message = "Please provide your first name")
    private String firstName;

    @Size(min = 2, max = 30, message = "Your last name '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message = "Please provide your last name")
    private String lastName;

    @Size(min = 10, max = 100, message = "Your address '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message = "Please provide your address")
    private String address;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    @NotNull(message = "Please provide phone number")
    private String phone;

    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
    private Date birthDate;

    @Email()
    @Size(min = 10, max = 80, message = "Your email '${validatedValue}' must be between {min} and {max} chars long")
    @NotNull(message = "Please provide email address")
    private String email;

    @NotNull(message = "Please provide password")
    private String password;


}
