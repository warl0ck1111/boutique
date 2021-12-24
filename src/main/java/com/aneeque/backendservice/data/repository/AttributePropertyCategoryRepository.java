package com.aneeque.backendservice.attributePropertyCategory;

import com.aneeque.backendservice.data.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */
public interface AttributePropertyCategoryRepository extends JpaRepository<AttributePropertyCategory,Long> {

    @Query("Select * from ")
    List<Attribute> getAttributesByCategoryId(Long categoryId);
}
