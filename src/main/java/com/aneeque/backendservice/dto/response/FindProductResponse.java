package com.aneeque.backendservice.dto.response;

/**
 * @author Okala Bashir .O.
 */



public interface FindProductResponse {

    Long getProductId();
    String getbrandName();
    Double getCostPrice();
    String getCreatedBy();
    Long getModifiedBy();
    String getName();
    String getProductCode();
    Integer getQuantity();
    Integer getReorderPoint();
    Integer getStockValue();
    Long getVendorId();
    String getColor();
    String getMaterialCareInfo();
    String getPreferredVendor();
    String getPriceType();
    String getSaleDuration();
    String getSaleStatus();
    String getSellingPrice();
    String getSizeAndFit();
    String getSizeCategory();
    Boolean getSizeAndMatch();
    Boolean getTrackInventory();
    String getUnit();
    String getTagDescription();
    String getTagName();
    String getTagId();

}
