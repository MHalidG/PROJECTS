package com.refugee.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="all_Npc")
public class NPC {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=16,nullable=false)
	private String name;
	
	
	private String nick;
	
	
	private String countryString;
	
	
	private MainCharacter ownerPlayer;
	

	
	
	
	
}
