package com.aneeque.backendservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class ShippingAddressDto {

    @JsonProperty("shippingAddressId")
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String city;
    private String country;
    private String landMark;
}
