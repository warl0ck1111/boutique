package com.aneeque.backendservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartResponseDto {

@JsonProperty("cartId")
    private Long id;
    private Long productId;
    private Long creatorId;
    private Long quantity;
    private Long orderId;
    private String uniqueId;
    private String productDescription;
    private String productName;
    private Double productPrice;
    private Set<String> fileNames = new HashSet<>();


//    public Double getTotalAmount() {
//        return this.cartQuantity * this.productPrice;
//    }
}
