package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.Loan;
import libdirector.dto.LoanDTO;

@Mapper(componentModel="spring")
public interface LoanMapper {

	Loan loanDTOToLoan(LoanDTO loanDTO);
	
}
