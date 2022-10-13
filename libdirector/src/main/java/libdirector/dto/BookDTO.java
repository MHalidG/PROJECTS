package libdirector.dto;

import java.io.File;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @Size(min = 2,max = 80,message="Size is exceeded")
    @NotNull(message = "Please provide book name")
    private String name;

    @NotNull(message = "Please provide isbn")
    @Size(max = 17)
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d$",
            message = "Please provide valid isbn number")
    private String isbn;

    private Integer pageCount;

    @NotNull(message = "Please provide a Author id")
    private Long bookAuthor;

    @NotNull(message = "Please provide a Publichser id")
    private Long bookPublisher;

    private Integer publishDate;

    @NotNull(message = "Please provide A Category id")
    private Long bookCategory;

    private File image;


    @NotNull(message = "Please provide shelf Code")
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^[A-Z]{2}-\\d{3}$",
            message = "Please provide a valid shelf Code")
    private String shelfCode;

    @NotNull(message = "Please provide shelf Code")
    private Boolean featured = false;




}
