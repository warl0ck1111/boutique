package com.aneeque.backendservice.dto.response;

import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */
public interface FindAllProductResponse {
    Long getProductId();
    String getName();
    String getDescription();
    Long getCategoryId();
    String getCategoryName();

    Integer getStockValue();
    Double getCostPrice();
    Double getPrice();
    LocalDateTime getCreatedAt();
    String getSellingPrice();

    String getTagName();
    String getFileName();
    String getFileType();

}
