package com.aneeque.backendservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class CartProductPropertiesDto {
    private Long productId;

    private Long propertiesId;

    private Long cartId;

}
