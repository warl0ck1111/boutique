package com.aneeque.backendservice.dto.request;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.data.entity.ProductProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;

    private String description;

    private int categoryId;

    private int quantity;

    private int stockValue;

    private int reorderPoint;

    private Double costPrice;

    private Double price;

    private Long vendorId;

    private String brandName;

    private String productCode;

    private List<Long> tagIds;

    List<ProductSizeInformationRequestDto> productSizeInformation = new ArrayList<>();

    List<ProductPropertiesRequestDto> productProperties = new ArrayList<>();

    private Long createdBy;

    private Long modifiedBy;



    

}
