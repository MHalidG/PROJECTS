package libdirector.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import libdirector.domain.Book;
import libdirector.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

	private Long id;

	@NotNull(message="User can not null")
	private User userLoan;

	@NotNull(message="Book can not null")
	private Book loanedBooks;

	@NotNull(message= "Please provide loan date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone= "Turkey")
	private LocalDateTime loanDate;

	@NotNull(message= "Please provide expire date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone= "Turkey")
	private LocalDateTime expireDate;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss", timezone= "Turkey")
	private LocalDateTime returnDate;
	
	@Size(max=300)
	private String notes;
}
