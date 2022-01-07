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
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Long ProductId;
    private Long tagId;

    public ProductTag(Long productId, Long tagId) {
        ProductId = productId;
        this.tagId = tagId;
    }
}
