package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CheckoutDTO {

    private Double totalAmount;

    @NotNull(message = "delivery method Field can not be empty")
    @NotBlank(message = "delivery method Field can not be empty")
    private String deliveryMethod;

    @NotNull(message = "payment method Field can not be empty")
    @NotBlank(message = "payment method Field can not be empty")
    private String paymentMethod;

    @NotNull(message = "uniqueId Field can not be empty")
    @NotBlank(message = "uniqueId Field can not be empty")
    private String uniqueId;

    @NotNull(message = "email Address Field can not be empty")
    @NotBlank(message = "email Address Field can not be empty")
    private String emailAddress;

    private ShippingAddressDto shippingAddressDetail;
    private BillingAddressDto billingAddressDetail;

//    @NotNull(message = "transactionRef Field can not be empty")
//    @NotBlank(message = "transactionRef Field can not be empty")
    private String transactionRef;

    private String status;
}
