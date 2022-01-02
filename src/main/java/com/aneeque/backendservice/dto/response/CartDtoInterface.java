package com.aneeque.backendservice.dto.response;

import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */

public interface CartDtoInterface {

    Long getCartId();

    Long getProductId();

    String getProductName();

    String getProductDescription();

    Double getProductPrice();

    String getCreatorFirstName();

    String getCreatorLastName();

    Double getCartQuantity();

    String getCartUniqueId();

    LocalDateTime getCartCreatedAt();


}
