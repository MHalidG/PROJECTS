package com.refugee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.refugee.domain.MainCharacter;

@Repository
public interface CharacterRepository extends JpaRepository<MainCharacter, Long>{

}
