package libdirector.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.repository.AuthorRepository;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;


    public Book saveBook(BookRequest bookRequest) {


        Book book = new Book();

        book.setName(bookRequest.getName());
        book.setIsbn(bookRequest.getIsbn());
        book.setPageCount(bookRequest.getPageCount());
        book.setPublishDate(bookRequest.getPublishDate());

        book.setBookAuthor(authorService.getAuthorById(bookRequest.getBookAuthor()));
        book.setBookPublisher(publisherService.getPublisherById(bookRequest.getBookPublisher()));
        book.setBookCategory(categoryService.getCategoryById(bookRequest.getBookCategory()));

        book.setLoanable(true);
        book.setShelfCode(bookRequest.getShelfCode());
        book.setActive(true);
        book.setFeatured(bookRequest.getFeatured());

        LocalDateTime today = LocalDateTime.now();

        book.setCreateDate(today);

        book.setBuiltIn(false);


        bookRepository.save(book);

        return book;

    }


}