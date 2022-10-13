package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Author;
import libdirector.dto.AuthorDTO;
import libdirector.repository.AuthorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository authorRepository;

    public Author saveAuthor(AuthorDTO authorDTO){
        Author author=new Author();
        author.setName(authorDTO.getName());
        author.setBuiltIn(authorDTO.getBuiltIn());
        authorRepository.save(author);
        return author;

    }

    public Author getAuthorById(Long id) {

        return authorRepository.findById(id).orElseThrow(()-> new RuntimeException("No Found"));

    }
}