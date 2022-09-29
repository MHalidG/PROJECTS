package hh.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	@NotNull(message="Kullanici Adi Bos Birakilamaz")
	@Size(min=16,max=32,message="Your Password '${validatedValue}' must be between {min} and {max} chars long")
	private String userName;
	

	//TO DO: Email unique kontrolu yapicaz.
	@Column
	@NotNull(message="Email Bos Birakilamaz")
	private Email email;
	
	@Column
	@NotNull(message="Sifre Bos Birakilamaz")
	@Size(min=16,max=32,message="Your Password '${validatedValue}' must be between {min} and {max} chars long")
	private String password;
	

	//TO DO: Phone Number unique kontrolu yapicaz.
	@Column(length = 22,nullable = false)
	private String phoneNumber;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="users_autorisation",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();
	
	
	
	
	
	
}
	
