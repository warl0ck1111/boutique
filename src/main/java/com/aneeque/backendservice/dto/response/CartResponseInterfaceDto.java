package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

public interface CartResponseInterfaceDto {

    Long getId();
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
