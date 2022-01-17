package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    @Modifying
    @Query(value = "DELETE from product_tag  where product_id = :productId AND tag_name = :tagName ", nativeQuery = true)
    Integer deleteProductTag(@Param("productId") Long productId, @Param("tagName") String tagName);
}
