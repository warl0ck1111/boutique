package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class LoginRequest {
    private String emailAddress;
    private String password;
}
