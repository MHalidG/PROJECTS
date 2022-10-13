package libdirector.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 30,nullable=false)
	private String firstName;

	@Column(length = 30,nullable=false)
	private String lastName;

	@Column(nullable=false)
	private Integer score=0;

	@Column(length = 100,nullable=false)
	private String address;

	@Column(nullable=false)
	private String phone;
	
	@Column
	private Date birthDate;

	@Column(length = 80, nullable = false, unique = true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private LocalDateTime createDate;
	
	@Column
	private String resetPasswordCode;
	
	@Column(nullable=false)
	private Boolean builtIn=false;

	@OneToMany(mappedBy = "userLoan")
	private List<Loan> userBooks=new ArrayList<>();


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="tbl_userRoles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();

	
}