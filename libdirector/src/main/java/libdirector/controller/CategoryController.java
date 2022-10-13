package libdirector.controller;

import java.util.Locale.Category;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;



    @PostMapping("/add")
    //TO DO: PreAuthorize()admin eklenecek
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(categoryService.saveCategory(categoryDTO),HttpStatus.CREATED);

    }
}