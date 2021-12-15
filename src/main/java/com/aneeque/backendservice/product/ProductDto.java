package com.aneeque.backendservice.product;

import com.aneeque.backendservice.category.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    @NotNull(message = "product price field can not be null")
    @NotEmpty(message = "product price field can not be empty")
    @NotBlank(message = "product name field can not be blank")
    private String name;


    @NotNull(message = "product price field can not be null")
    @NotEmpty(message = "product price field can not be empty")
    @NotBlank(message = "product description field can not be blank")
    private String description;


    @NotNull(message = "product price field can not be null")
    @NotEmpty(message = "product price field can not be empty")
    @NotBlank(message = "product category field can not be blank")
    @JsonProperty("categoryId")
    private Long category;

    @NotNull(message = "product price field can not be null")
    @NotEmpty(message = "product price field can not be empty")
    @NotBlank(message = "product price can not be blank")
    private Double price;

}
