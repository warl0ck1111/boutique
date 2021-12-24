package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */
public interface AttributePropertyCategoryRepository extends JpaRepository<com.aneeque.backendservice.attributePropertyCategory.AttributePropertyCategory,Long> {

    @Query(value = "Select a.* from attribute a where category_id = :categoryId", nativeQuery = true)
    List<Attribute> getAttributesByCategoryId(Long categoryId);

    @Query(value = "Select p.* from property p where category_id = :categoryId", nativeQuery = true)
    List<Property> getPropertyByCategoryId(Long categoryId);

}
