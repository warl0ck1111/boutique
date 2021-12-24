package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartCreateRequestDto {
    private Long productId;
    private Long creatorId;
    private Long quantity;
    private String uniqueId;
}
