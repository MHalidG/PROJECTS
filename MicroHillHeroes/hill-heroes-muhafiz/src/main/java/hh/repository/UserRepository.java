package hh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hh.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
