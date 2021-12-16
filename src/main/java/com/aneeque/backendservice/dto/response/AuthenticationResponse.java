package com.aneeque.backendservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Okala III
 */

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
    private String role;
    private List<String> privileges;
    private String userId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLogin;


}
