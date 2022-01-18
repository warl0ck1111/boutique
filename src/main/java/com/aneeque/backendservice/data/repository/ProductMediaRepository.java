package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.ProductMedia;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductImageRepository extends JpaRepository<ProductMedia,Long> {

    @Query(value = "SELECT p.created_at AS createdAt, p.description, p.modified_at AS modifiedAt, " +
            "p.name, p.price, p.category_id AS categoryId, p.brand_name AS brandName, " +
            "p.product_code AS productCode, p.vendor_id AS vendorId, p.modified_by AS modifiedBy, " +
            "p.category_id as categoryId, p.created_by AS createdBy, pi.file_name AS fileName " +
            "FROM product_image pi LEFT JOIN product p on pi.product_id = p.id where pi.product_id = :productId", nativeQuery = true)
    List<ProductResponseDto> findAllByProductId(@Param("productId") Long productId);

    @Modifying
    @Query(value = "INSERT INTO product_image ( created_at, file_name, modified_at, product_id) VALUE(:createdAt, :fileName, :modifiedAt, :productId)", nativeQuery = true)
    Integer addMediaFile(  @Param("createdAt") String createdAt, @Param("fileName") String fileName, @Param("modifiedAt") String modifiedAt, @Param("productId") Long productId);

    @Modifying
    @Query(value = "DELETE FROM product_image where product_id = :productId", nativeQuery = true)
    Integer removeAllProductImagesByProductId(@Param("productId") Long productId);

}
