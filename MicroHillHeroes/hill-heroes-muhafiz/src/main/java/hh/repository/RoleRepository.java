package hh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hh.domain.Role;
import hh.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role>findByName(RoleType name);
	
}
