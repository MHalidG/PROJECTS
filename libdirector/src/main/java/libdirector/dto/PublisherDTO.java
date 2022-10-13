package libdirector.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherDTO {



    @NotNull(message = "Please provide Publisher Name")
    @Size(min=2,max=50,message="Publisher Name '${validatedValue}' must be between {min} and {max} chars long")
    private String name;

    @NotNull(message="Please provide BuiltIn")
    Boolean builtIn =false;



}