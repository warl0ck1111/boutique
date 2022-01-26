package com.aneeque.backendservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

public interface CartResponseInterfaceDto {

    Long getId();
    Long getCartId();
    Long getProductId();
    Long getCreatorId();
    Long getQuantity();
    Long getOrderId();
    String getUniqueId();
    String getProductDescription();
    String getProductName();
    Double getProductPrice();
    String getFileName();

}
