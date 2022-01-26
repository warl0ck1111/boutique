package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartResponseDto {

    private Long id;
    private Long productId;
    private Long creatorId;
    private Long quantity;
    private Long orderId;
    private String uniqueId;
    private String productDescription;
    private String productName;
    private Double productPrice;

//    public Double getTotalAmount() {
//        return this.cartQuantity * this.productPrice;
//    }
}
