package com.aneeque.backendservice.dto.response;

import java.time.LocalDateTime;

/**
 * @author Okala Bashir .O.
 */



public interface FindProductResponse {

    Long getProductId();
    String getName();
    String getDescription();
    Long getVendorId();
    Long getCategoryId();
    String getCategoryName();
    Integer getQuantity();
    Integer getStockValue();
    Integer getReorderPoint();
    Double getCostPrice();
    String getbrandName();
    String getProductCode();
    Double getPrice();
    Long getCreatedBy();
    LocalDateTime getCreatedAt();
    Long getModifiedBy();
    String getMaterialCareInfo();
    String getSellingPrice();
    String getPriceType();
    String getSaleStatus();
    String getSaleDuration();
    Boolean getTrackInventory();
    String getTagName();
    String getFileName();
    String getFileType();


}
