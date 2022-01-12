package com.aneeque.backendservice.attributePropertyCategory;

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
public class AttributePropertyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long attributePropertyId;

    private long categoryId;

}
