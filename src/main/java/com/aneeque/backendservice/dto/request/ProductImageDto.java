package com.aneeque.backendservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDto {

    private String fileName;

    private Long productId;
    

}
