package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Okala Bashir .O.
 */

@Data
@Slf4j
public class BillingAddressDto {

    @NotBlank(message = "first name can not be blank" )
    @NotNull(message = "first name can not be empty")
    private String firstName;

    @NotBlank(message = "last name can not be blank" )
    @NotNull(message = "last name can not be empty")
    private String lastName;

    @NotBlank(message = "email Address can not be blank" )
    @NotNull(message = "email Address can not be empty")
    private String emailAddress;

    @NotBlank(message = "mobile Number can not be blank" )
    @NotNull(message = "mobile Number can not be empty")
    private String mobileNumber;

    @NotBlank(message = "city can not be blank" )
    @NotNull(message = "city name can not be empty")
    private String city;


    @NotBlank(message = "country can not be blank" )
    @NotNull(message = "country can not be empty")
    private String country;

}
