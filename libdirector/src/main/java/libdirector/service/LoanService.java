package libdirector.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.domain.User;
import libdirector.dto.LoanDTO;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;

@Service
public class LoanService {
	
	private BookRepository bookRepository;
	
	
public void createLoan(LoanDTO loanDTO, Long userId,Long bookId) {
		
		checkReservationTimeIsCorrect(loanDTO.getLoanDate(), loanDTO.getExpireDate());
		
		Book book=bookRepository.findById(bookId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, bookId)));
		
		boolean carStatus=checkCarAvailability(carId, reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());
		
		User user=userRepository.findById(userId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));
		
		Reservation reservation=reservationMapper.reservationRequestToReservation(reservationRequest);
		
		if(!carStatus) {
			reservation.setStatus(ReservationStatus.CREATED);
		}else {
			throw new BadRequestException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
		}
		reservation.setCarId(car);
		reservation.setUserId(user);
		
		Double totalPrice = getTotalPrice(carId, reservation.getPickUpTime(), reservation.getDropOffTime());
		reservation.setTotalPrice(totalPrice);
		
		reservationRepository.save(reservation);
		
	}

private void checkReservationTimeIsCorrect(LocalDateTime loanDate,LocalDateTime expireDate) {
	
	
}
	

}
