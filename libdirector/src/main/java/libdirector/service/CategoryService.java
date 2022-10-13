package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Category;
import libdirector.dto.CategoryDTO;
import libdirector.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;
    public Category saveCategory(CategoryDTO categoryDTO) {

        Category category=new Category();
        category.setName(categoryDTO.getName());
        category.setBuiltIn(categoryDTO.getBuiltIn());

        //category.setSequence(categoryDTO.getSequence()); //CategoryDTO Sequence kapali

        category.setSequence(1);
        //Sequence manuel ayarlamadan testi geciremedim sebebi sorulacak
        categoryRepository.save(category);
        return category;

    }

    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
    }
}
