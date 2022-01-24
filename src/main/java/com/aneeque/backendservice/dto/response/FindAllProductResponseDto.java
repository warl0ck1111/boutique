package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.dto.request.ProductMediaDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
public class FindAllProductResponseDto {

    private Long productId;

    private String name;

    private String description;

    private Set<String> categoryNames = new HashSet<>();

    private Integer stockValue;

    private Double costPrice;

    private Double price;

    private LocalDateTime createdAt;

    private String sellingPrice;

    private Set<String> tags = new HashSet<>();

    private Set<ProductMediaDto> mediaFiles = new HashSet<>();

}