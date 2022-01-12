package com.aneeque.backendservice.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;


    public Product(String name, String description, Long category, Double price, Long createdBy) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdBy = createdBy;
    }

}
