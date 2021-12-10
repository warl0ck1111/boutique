package com.aneeque.backendservice.appuser;

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
