package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class OrderItemProductPropertiesDto {
    private Long productId;
    private Long propertyId;
    private Long orderItemId;
}