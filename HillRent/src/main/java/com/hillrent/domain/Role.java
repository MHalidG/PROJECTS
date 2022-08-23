package com.hillrent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hillrent.domain.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="tbl_role")
public class Role {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)//oridinal secersen enumlarin sayisini getirir
	//boyle olursa db de bakinca anlayamayiz hangi role oldugunu
	@Column(length=20)
	private RoleType name;

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}
	
	
}
