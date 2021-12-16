package com.aneeque.backendservice.dto.request;

import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.data.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

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
