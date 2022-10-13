package libdirector.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> createBook(@Valid @RequestBody BookRequest bookRequest)  {

        bookService.saveBook(bookRequest);

        Map<String,String> map=new HashMap<>();
        map.put("message", "Book Successfully Created");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }






}
