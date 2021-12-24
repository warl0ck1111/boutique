package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartResponseDto {

    private Long productId;
    private Long creatorId;
    private Long quantity;
    private String uniqueId;
}
