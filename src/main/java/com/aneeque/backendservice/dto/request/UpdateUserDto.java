package com.aneeque.backendservice.dto.request;


import com.aneeque.backendservice.util.validator.ConfirmPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author B.O Okala III
 */

@Data
@NoArgsConstructor
@ConfirmPassword(passwordField="password", confirmPasswordField = "confirmPassword", message = "the password mismatch")
@AllArgsConstructor
public class UpdateUserDto {

    @NotBlank(message = "first name field can not be empty")
    private String firstName;

    @NotBlank(message = "last name field can not be empty")
    private String lastName;

    @Email(message = "invalid email")
    @NotBlank(message = "email address field can not be empty")
    private String emailAddress;


    @NotBlank(message = "mobile number field can not be empty")
    private String mobileNumber;

    private String address;

    private String city;

    private String country;

    private String dateOfBirth;

    private String gender;

    private String jobDescription;

    private String jobTitle;

    private String maritalStatus;

    private String title;

    private String postCode;


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.toLowerCase();
    }
}
