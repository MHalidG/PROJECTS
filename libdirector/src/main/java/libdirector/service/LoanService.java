package libdirector.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.hillrent.domain.Reservation;
import com.hillrent.domain.enums.ReservationStatus;

import libdirector.domain.Book;
import libdirector.domain.Loan;
import libdirector.domain.User;
import libdirector.dto.LoanDTO;
import libdirector.dto.mapper.LoanMapper;
import libdirector.exception.BadRequestException;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;
import libdirector.repository.LoanRepository;
import libdirector.repository.UserRepository;

@Service
public class LoanService {
	
	private BookRepository bookRepository;
	private UserRepository userRepository;
	private LoanMapper loanMapper;
	private LoanRepository loanRepository;
public void createLoan(LoanDTO loanDTO, Long userId,Long bookId) {
		
	/*Skor kullanici kayit oldugunda default olarak sifir ile baslayacak. 
	daha sonra kullanicinin davranisina gore bu deger + veya - olarak degisecek. 
	zamaninda teslim edilen kitap +, teslim edilmeyen kitap ise - olarak kullaniciya kaydedilecek. 
	default olarak 0 ile baslayan kisi tabloya gore ayni anda 3 kitap olabilir. 
	yani tablo kullanicinin puanina gore kac kitabi ne kadar sure ile alacagini hesaplamak icin. 
	kullanici kitap alirken kendi puanina gore bir expected return time.i olacak.kullanici kitabi geri getirdiginde
	 tarih kontrolu yapacagiz. eger tamam ise + puan alacak.bu degerler maximum +2 / -2 olabilir. 
	 Sequence siralama olacak. front-end kisminda kategorilerin silamasi olacak. 
	her zaman sequence siralamasina gore api.den response tasarlayacagiz.*/
	
		checkLoanTimeIsCorrect(loanDTO.getLoanDate(), loanDTO.getExpireDate(),loanDTO.getReturnDate());
		
		Book book=bookRepository.findById(bookId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, bookId)));
		
				if(!book.getLoanable()) {
					throw new ResourceNotFoundException(String.format(ErrorMessage.BOOK_NOT_AVAILABLE_MESSAGE, bookId));
				}
						
		User user=userRepository.findById(userId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));
		
		//TODO:User score ExpireDate logic kurulacak. 		user.get
		
		
		Loan loan=loanMapper.loanDTOToLoan(loanDTO);
		
		loan.setLoanedBooks(book);
		loan.setUserLoan(user);
		
		/*Double total= getTotalPrice(carId, reservation.getPickUpTime(), reservation.getDropOffTime());
		reservation.setTotalPrice(totalPrice);*/
		
		loanRepository.save(loan);
		
	}





private void checkLoanTimeIsCorrect(LocalDateTime loanDate,LocalDateTime expireDate, LocalDateTime returnDate) {
	LocalDateTime now=LocalDateTime.now();
	
	if(loanDate.isBefore(now)) {
		throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
	}
	
	boolean isEqual=loanDate.isEqual(returnDate)?true:false;
	boolean isBefore=loanDate.isBefore(returnDate)?true:false;
	
	if(isEqual||!isBefore) {
		throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
	}	
}
	






}
