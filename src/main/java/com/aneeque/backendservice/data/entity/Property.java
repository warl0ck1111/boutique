package com.aneeque.backendservice.data.entity;

import com.aneeque.backendservice.data.entity.Attribute;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Entity
@Data
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("propertyId")
    private Long id;

    private String description;

    private String data;

    private String productName;

    private int categoryId;

    private int totalQuantity;

    private int totalStockValue;

    private int reorderPoint;

    private Double costPrice;

    private Double price;

    private Long vendorId;

    private String brandName;

    private String productCode;

    private String tags;

    private String materialCareInfo;

    private String sizeAndFit;

    private String color;

    private String sellingPrice;

    private String priceType;

    private String saleStatus;

    private String saleDuration;

    private boolean trackInventory;

    private String preferredVendor;

    private boolean sizeMatch;

    private String sizeCategory;

    private String unit;

    private String productImage;

    private String productVideo;

    @JsonBackReference
    @ManyToMany(mappedBy = "properties")
    private List<Attribute> attributes;


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;

    public Property(String description, String data) {
        this.description = description;
        this.data = data;
    }
}
