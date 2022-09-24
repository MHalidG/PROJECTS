package com.tpe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="t_car")
public class Car {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=30,nullable=false)
	private String brand;
	
	@Column(length=30,nullable=false)
	private String model;
	
	@Column(nullable=false)
	private Integer doors;

	@Column(nullable=false)
	private Double pricePerHour;
	
	@Column(nullable=false)
	private Integer age;



}
