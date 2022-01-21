package com.aneeque.backendservice.dto.response;

import com.aneeque.backendservice.data.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @JsonProperty("productId")
    private Long id;

    private String name;

    private String description;

    private Long vendorId;

    private Long categoryId;


    private int quantity;

    private int stockValue;

    private int reorderPoint;

    private Double costPrice;

    private String brandName;

    private String productCode;

    private Double price;

    private Long createdBy;

    private Long modifiedBy;

    private String materialCareInfo;

    private String sellingPrice;

    private String priceType;


    private String saleStatus;

    private String saleDuration;

    private boolean trackInventory;

    private String preferredVendor;

    private String unit;

    private Set<String> tags = new HashSet<>();
    private Set<String> mediaFiles = new HashSet<>();
    private Set<Long> categories = new HashSet<>();




}
