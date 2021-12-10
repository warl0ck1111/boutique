package com.aneeque.backendservice.appuser;

import com.aneeque.backendservice.privilege.Privilege;
import com.aneeque.backendservice.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class AppUserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String password;

    private String mobileNumber;

    @JsonIgnore
    private int failedLoginAttempt; //(MAX = 10, )

    private Role role;

    private Collection<Privilege> userAssignedPrivileges;

    private Boolean locked;

    private Boolean enabled;

    private Boolean isDeleted;

}
