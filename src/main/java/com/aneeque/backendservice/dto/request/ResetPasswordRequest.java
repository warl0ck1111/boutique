package com.aneeque.backendservice.dto.request;


import lombok.Data;

/**
 * @author B.O Okala III
 */

@Data
public class ResetPasswordRequest {
    private final String email;
    private final String password;
    private final String confirmPassword;
}
