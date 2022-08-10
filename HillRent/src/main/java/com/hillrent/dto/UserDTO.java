package com.hillrent.dto;


import java.util.HashSet;
import java.util.Set;

import com.hillrent.domain.Role;
import com.hillrent.domain.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String email;

	private String phoneNumber;

	private String addres;

	private String zipCode;

	private boolean builtIn;

	private Set<String> roles;
	
	public void setRoles(Set<Role> roles) {
		
		Set<String> rolesStr=new HashSet<>();
		
		roles.forEach(r->{
			if(r.getName().equals(RoleType.ROLE_ADMIN))
				rolesStr.add("Administrator");
			else
				rolesStr.add("Customer");
		});
		this.roles=rolesStr;
	}
	
}