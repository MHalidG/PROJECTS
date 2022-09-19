package com.refugee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_mainChar")
public class MainCharacter {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(length= 36, nullable= false)
	private String name;
	
	
	private String position;

	
	private String swordsSkillType; //Kilic sanatlarini olusturup birbirlerine ustunluklerini ayarlicaz
	
	
	private String intelligentType;	//Tuccar, Siyasi, Savasci, Casus, Emekci, Suclu, 
	
	//Her Zeka Turunun kendine has alt puanlari olacak,
	//Her klass kendi pointlerinde kasmaya calismali. Level sistemi lazim.
	
	

	
	
	
}
