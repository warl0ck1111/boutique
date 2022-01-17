package com.aneeque.backendservice.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Okala Bashir .O.
 */

@Entity
@Data
@NoArgsConstructor
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long ProductId;
    private String tagName;

    public ProductTag(Long productId,String tagName) {
        ProductId = productId;
        this.tagName = tagName;
    }
}
