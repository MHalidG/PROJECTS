package libdirector.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import libdirector.dto.LoanDTO;
import libdirector.service.LoanService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

	private LoanService loanService;
	
	
	@PostMapping()
	//@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public ResponseEntity<LoanDTO> makeLoan(HttpServletRequest request,
		@RequestParam(value = "bookId") Long bookId, @Valid @RequestBody LoanDTO loanDTO) {
		Long user = (Long) request.getAttribute("id");
		loanService.createLoan(loanDTO, user,bookId );

		
		return new ResponseEntity<>(loanDTO, HttpStatus.CREATED);
	}
	
	
}
