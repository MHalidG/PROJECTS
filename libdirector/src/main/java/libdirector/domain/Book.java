package libdirector.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_books")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(length = 80, nullable=false)
    private String name;

    @Column(length = 17, nullable=false)
    private String isbn;

    @Column
    private Integer pageCount;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="authorId", nullable=false)
    private Author bookAuthor;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="publisherId", nullable=false)
    private Publisher bookPublisher;


    @Column
    private Integer publishDate;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="categoryId", nullable=false)
    private Category bookCategory;



    @Column
    private File image;

    @Column(nullable=false)
    private Boolean loanable = true;

    @Column(length = 6, nullable=false)
    private String shelfCode;

    @Column(nullable=false)
    private Boolean active = true;

    @Column(nullable=false)
    private Boolean featured = false;

    @Column(nullable=false)
    private LocalDateTime createDate;

    @Column(nullable=false)
    private Boolean builtIn =false;

    @OneToMany(mappedBy = "loanedBooks")
    private List<Loan> loanedBooks=new ArrayList<>();







}