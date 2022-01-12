package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.data.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {


    private String name;


    private String description;


    private Long categoryId;


    private Double price;

    private Long id;

    private Long vendorId;

    private String brandName;

    private String productCode;

    private String url;

    private String fileName;


}
