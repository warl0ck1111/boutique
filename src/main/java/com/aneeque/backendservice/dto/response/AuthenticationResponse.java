package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.data.entity.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Okala III
 */

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
    private String role;
    private Set<Privilege> privileges;
    private String userId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLogin;
    private String emailAddress;


}
