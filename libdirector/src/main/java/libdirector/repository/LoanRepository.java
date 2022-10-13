package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libdirector.domain.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

}
