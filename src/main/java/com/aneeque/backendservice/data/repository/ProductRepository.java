package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.dto.response.ProductPropertyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "SELECT  ", nativeQuery = true)
//    List<ProductPropertyResponseDto> findAllByPropertiesIn(List<Long> propertyIds);

    @Query(value = "select id, brand_name, category_id, cost_price, created_at, created_by, description, modified_at, modified_by, name, price, product_code, quantity, reorder_point, stock_value, vendor_id from product ", nativeQuery = true)
    List<Product> findAllProducts(Pageable pageable);


    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO product ( brand_name, category_id, cost_price, " +
            "created_at, created_by, description, name, price, product_code, quantity, " +
            "reorder_point, stock_value, vendor_id, modified_at, modified_by) VALUES ( :brandName, :categoryId, :costPrice, :createdAt, :createdBy, " +
            ":description :name, :price, :productCode, :quantity, :reorderPoint, :stockValue, :vendorId, :modifiedAt, :modifiedBy)")
    Integer createProduct(@Param("brandName") String brandName, @Param("categoryId") int categoryId,
                          @Param("createdAt") String createdAt, @Param("createdBy") Long createdBy,
                          @Param("description") String description, @Param("name") String name,
                          @Param("price") Double price, @Param("productCode") String productCode,
                          @Param("vendorId") Long vendorId, @Param("costPrice") Double costPrice,
                          @Param("quantity") int quantity, @Param("reorderPoint") int reorderPoint,
                          @Param("stockValue") int stockValue, Long modifiedBy, String modifiedAt);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE product SET brand_name= :brandName, category_id=:categoryId, cost_price=:costPrice, " +
            "created_at=:createdAt, created_by=:createdBy, description=:description, modified_at=:modifiedAt, modified_by=:modifiedBy, name=:name, price=:price, product_code=:productCode, quantity=:quantity, " +
            "reorder_point=:reorderPoint, stock_value=:stockValue, vendor_id=:vendorId WHERE id = :productId")
    Integer updateProduct(@Param("productId") Long productId, @Param("brandName") String brandName, @Param("categoryId") int categoryId,
                          @Param("createdAt") String createdAt, @Param("createdBy") Long createdBy,
                          @Param("description") String description, @Param("name") String name,
                          @Param("price") Double price, @Param("productCode") String productCode,
                          @Param("vendorId") Long vendorId, @Param("costPrice") Double costPrice,
                          @Param("quantity") int quantity, @Param("reorderPoint") int reorderPoint,
                          @Param("stockValue") int stockValue, Long modifiedBy, String modifiedAt);

    @Modifying
    @Query(value = "SELECT * FROM product where id=:productId", nativeQuery = true)
    Integer findProductById(@Param("productId") Long productId);

    @Modifying
    @Query(value = "DELETE FROM product where id=:productId", nativeQuery = true)
    Integer deleteProductById(@Param("productId") Long productId);


    @Modifying
    @Query(value = "INSERT INTO product_properties (product_id, property_id, price ) value (:productId, :propertyId, :price)", nativeQuery = true)
    Integer addProductProperty(@Param("productId") Long productId, @Param("propertyId") Long propertyId, @Param("price") Double price);

    @Modifying
    @Query(value = "DELETE FROM product_properties WHERE product_id = :productId AND property_id = :propertyId", nativeQuery = true)
    Integer removeProductProperty(@Param("productId") Long productId, @Param("propertyId") Long propertyId);


}
