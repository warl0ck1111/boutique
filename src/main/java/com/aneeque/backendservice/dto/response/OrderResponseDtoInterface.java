package com.aneeque.backendservice.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */
public interface OrderResponseDtoInterface {
    String getId();
    String getCustomerName();
    Double getTotalAmount();
    String getDeliveryMethod();
    String getPaymentMethod();
    String getUniqueId();
    String getEmailAddress();
    String getTransactionRef();
    String status();
    LocalDateTime getCreatedAt();

}
