package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class OrderResponseDTO {

    public String id;
    String customerName;
    private Double totalAmount;
    private String deliveryMethod;
    private String paymentMethod;
    private String uniqueId;
    private String emailAddress;
    private String transactionRef;
    private Long shippingAddressId;
    private Long billingAddressId;
    private String status;
    private int itemCount;


}
