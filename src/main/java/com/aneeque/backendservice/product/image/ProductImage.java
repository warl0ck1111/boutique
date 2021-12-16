package com.aneeque.backendservice.product.image;

import com.aneeque.backendservice.data.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Okala III
 */

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("productImageId")
    private Long id;

    private String fileName;

    @OneToOne
    private Product product;

    private String imageUrl;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;

}
