package libdirector.domain;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId", nullable=false)
    private User userLoan;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="bookId", nullable=false)
    private Book loanedBooks;


    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    @Column
    private LocalDateTime returnDate;

    @Column(length = 300)
    private String notes;
}