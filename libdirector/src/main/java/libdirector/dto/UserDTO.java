package libdirector.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import libdirector.domain.Loan;
import libdirector.domain.Role;
import libdirector.domain.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer score=0;

    private String address;

    private String phone;

    private Date birthDate;

    private String email;

    private String password;

    private LocalDateTime createDate;

    private String resetPasswordCode;

    private Boolean builtIn=false;

    private List<Loan> userBooks=new ArrayList<>();

    private Set<String> roles;

    public void setRoles(Set<Role> roles){
        Set<String> rolesStr = new HashSet<>();

        roles.forEach(r -> {
            if (r.getName().equals(RoleType.ROLE_ADMIN)) {
                rolesStr.add("Administrator");
            } else if (r.getName().equals(RoleType.ROLE_MEMBER)) {
                rolesStr.add("Member");
            } else {
                rolesStr.add("Staff");
            }

        });

        this.roles = rolesStr;

    }


}