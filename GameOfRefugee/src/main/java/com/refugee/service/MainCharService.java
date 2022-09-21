package com.refugee.service;

import org.springframework.stereotype.Service;

import com.refugee.domain.MainCharacter;
import com.refugee.repository.CharacterRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class MainCharService {

	private CharacterRepository charRepository;
	
	public void saveChar(MainCharacter character) {
		
		charRepository.save(character);
		
	}
	
}
