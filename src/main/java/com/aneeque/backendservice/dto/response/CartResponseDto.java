package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartResponseDto {

    private Long cartId;
    private Long productId;
    private Long creatorId;
    private Long quantity;
    private String cartUniqueId;
    private String cartCreatedAt;
    private int cartQuantity;
    private String productDescription;
    private String productName;
    private Double productPrice;

//    public Double getTotalAmount() {
//        return this.cartQuantity * this.productPrice;
//    }
}
