package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.ProductSizeInformation;
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
public interface ProductSizeInformationRepository extends JpaRepository<ProductSizeInformation, Long> {

    @Modifying
    @Query(value = "INSERT INTO product_size_information (category, name, unit, from_x, to_y, product_Id) " +
            "VALUES (:category, :name, :unit, :fromX, :toY, :productId)", nativeQuery = true)
    Integer createProductSizeInformation(@Param("category") String category, @Param("name") String name, @Param("unit")
            String unit, @Param("fromX") Double fromX, @Param("toY") Double toY, @Param("productId") Long productId);


    @Query(value = "SELECT *  FROM product_size_information WHERE product_id = :productId ", nativeQuery = true)
    List<ProductSizeInformation> getProductSizeInformationByProductId(@Param("productId") Long productId);

    @Modifying
    @Query(value = "UPDATE product_size_information SET category = :category, name=:name, unit=:unit, " +
            "from_x=:fromX, to_y = :toY, product_Id=:productId WHERE id = :prodSizeInfoId", nativeQuery = true)
    void updateProductSizeInformation(@Param("prodSizeInfoId") Long prodSizeInfoId, @Param("category") String category,
                                     @Param("name") String name, @Param("unit") String unit,
                                     @Param("fromX") Double fromX, @Param("toY") Double toY,
                                     @Param("productId") Long productId);

    @Modifying
    @Query(value = "DELETE FROM product_size_information WHERE id =:id ", nativeQuery = true)
    int deleteProductSizeInformation(@Param("id") Long prodSizeInfoId);
}
