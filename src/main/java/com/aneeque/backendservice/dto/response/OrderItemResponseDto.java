package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

public interface OrderItemResponseDto {

    String getOrderId();

    String getEmailAddress();

    String getOrderDeliveryMethod();

    String getOrderItemProductId();

    String getOrderItemQuantity();

    String getOrderItemStatus();

    String getOrderStatus();

    String getOrderTotalAmount();

    String getOrderTransactionRef();

    String getOrderUniqueId();

    String getProductDescription();

    String getProductName();

    String getProductPrice();

}
