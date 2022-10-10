package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import libdirector.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
