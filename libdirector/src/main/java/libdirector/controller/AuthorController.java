package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.domain.Author;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    AuthorService authorService;



    @PostMapping("/add")
    //TO DO: PreAuthorize()admin eklenecek
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody AuthorDTO authorDTO) {

        return new ResponseEntity<>(authorService.saveAuthor(authorDTO),HttpStatus.CREATED);

    }
}