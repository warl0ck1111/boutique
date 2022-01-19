package com.aneeque.backendservice.dto.response;

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
    Long getModifiedBy();
    String getMaterialCareInfo();
    String getSellingPrice();
    String getPriceType();
    String getSaleStatus();
    String getSaleDuration();
    Boolean getTrackInventory();
    String getPreferredVendor();
    String getUnit();
    String getTagName();
    String getFileName();


}
