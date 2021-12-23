package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class OrderItemDto {
    private long productId;
    private int quantity;
    private long creatorId;
    private String status;
    private String orderId;
}
