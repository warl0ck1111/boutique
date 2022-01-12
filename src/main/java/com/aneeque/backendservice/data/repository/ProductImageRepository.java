package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.data.entity.ProductImage;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {

    @Query(value = "SELECT p.created_at AS createdAt, p.description, p.modified_at AS modifiedAt, " +
            "p.name, p.price, p.category_id AS categoryId, p.brand_name AS brandName, " +
            "p.product_code AS productCode, p.vendor_id AS vendorId, p.modified_by AS modifiedBy, " +
            "p.category, p.created_by AS createdBy, pi.file_name AS fileName, pi.url " +
            "FROM product_image pi LEFT JOIN product p on pi.product_id = p.id where pi.product_id = :productId", nativeQuery = true)
    List<ProductResponseDto> findAllByProductId(@Param("productId") Long productId);
}
