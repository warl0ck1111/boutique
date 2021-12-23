package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartCreateRequestDto {
    private Long product;
    private Long creator;
    private Long quantity;
    private String uniqueId;
}
