package com.aneeque.backendservice.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */

public interface CartProductPropertiesDto {


    Long getCartId();

    Long getProductPropertyId();

    Long getProductId();

    String getProductName();

    String getProductDescription();

    Long getCartQuantity();

    String getCartUniqueId();

    String getProductPropertyData();

    String getProductPropertyDescription();


}
