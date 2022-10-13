package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libdirector.domain.Publisher;
import libdirector.domain.User;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

}
