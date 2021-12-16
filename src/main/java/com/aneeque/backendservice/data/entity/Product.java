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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("productId")
    private Long id;

    private String name;

    private String description;

    @OneToOne(optional = true)
    private Category category;

    private Double price;


    @OneToOne
    private AppUser createdBy;

    @OneToOne(optional = true)
    private AppUser ModifiedBy;

    @OneToMany
    private List<ProductImage> productImages;

    @OneToMany
    private List<Attribute> attributes;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany()
    @JoinTable(
            name = "PRODUCT_PROPERTIES",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id")
    )
    private List<Property> properties = new ArrayList<>();




    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd h:m:s")
    private LocalDateTime modifiedAt;


    public Product(String name, String description, Category category, Double price, AppUser createdBy) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.createdBy = createdBy;
    }

}
