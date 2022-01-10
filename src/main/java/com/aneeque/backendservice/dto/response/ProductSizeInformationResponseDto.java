package com.aneeque.backendservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class ProductSizeInformationResponseDto {

    private Long id;
    private String category;
    private String name;
    private String unit;
    private Double fromX;
    private Double toY;
    private Long productId;
}
