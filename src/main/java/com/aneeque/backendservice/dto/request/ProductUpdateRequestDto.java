package com.aneeque.backendservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDto {

    public boolean updateMediaFiles = false;
    public boolean updateTags = false;
    public boolean updateCategories = false;


    private String name;

    private String description;

    private Set<Long> categories;

    private Integer quantity;

    private Integer stockValue;

    private Integer reorderPoint;

    private Double costPrice;

    private Double price;

    private Long vendorId;

    private String brandName;

    private String productCode;

    private Set<String> tags;

    private Set<ProductMediaDto> mediaFiles;

    private String materialCareInfo;

    private Double sellingPrice;

    private String priceType;

    private String saleStatus;

    private String saleDuration;

    private Boolean trackInventory;


//    List<ProductSizeInformationRequestDto> productSizeInformation = new ArrayList<>();

//    List<ProductPropertiesRequestDto> productProperties = new ArrayList<>();

    private Long createdBy;

    private Long modifiedBy;





}
