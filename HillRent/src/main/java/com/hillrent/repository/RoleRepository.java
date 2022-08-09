package com.hillrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hillrent.domain.Role;
@Repository
//JpaRepoyu extend ettigimiz icin spring buranin repo oildugunu anliyor repo annot koymasak da olur normalde
// ama okuma kolayligi vs acidan konabilir
public interface RoleRepository extends JpaRepository<Role, Long>{

	
}
