package com.aneeque.backendservice.appuser;


import com.aneeque.backendservice.util.validator.ConfirmPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author B.O Okala III
 */

@Data
@NoArgsConstructor
@ConfirmPassword(passwordField="password", confirmPasswordField = "confirmPassword", message = "the password mismatch")
public class RegistrationRequest {

    @NotBlank(message = "first name field can not be empty")
    private String firstName;

    @NotBlank(message = "last name field can not be empty")
    private String lastName;

    @Email(message = "invalid email")
    @NotBlank(message = "email address field can not be empty")
    private String emailAddress;

    @NotBlank(message = "mobile number field can not be empty")
    private String mobileNumber;

    @NotBlank(message = "password field can not be empty")
    private String password;

    @NotBlank(message = "confirm password field can not be empty")
    private String confirmPassword;

}
