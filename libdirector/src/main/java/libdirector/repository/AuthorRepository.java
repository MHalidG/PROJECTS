package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libdirector.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}
