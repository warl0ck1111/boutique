package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.dto.response.FindProductResponse;
import org.springframework.data.domain.Pageable;
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
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT id,brand_name, category_id, cost_price, created_at, created_by, description, modified_at," +
            "                                 modified_by, name, price, product_code, quantity, reorder_point, stock_value, vendor_id, color," +
            "                                 material_care_info, preferred_vendor, price_type, sale_duration, sale_status, selling_price," +
            "                                 size_and_fit, size_category, size_match, track_inventory, unit from product ", nativeQuery = true)
    List<Product> findAllProducts(Pageable pageable);

    @Modifying
    @Query(value = "insert into product (brand_name, category_id, cost_price, created_at, created_by, description, modified_at," +
            "                     modified_by, name, price, product_code, quantity, reorder_point, stock_value, vendor_id, color," +
            "                     material_care_info, preferred_vendor, price_type, sale_duration, sale_status, selling_price," +
            "                     size_and_fit, size_category, size_match, track_inventory, unit)" +
            "values (:brandName, :categoryId, :costPrice, :createdAt, :createdBy, :description,:modifiedAt," +
            ":modifiedBy, :productName, :price, :productCode, :quantity, :reorderPoint, :stockValue, :vendorId, :color," +
            ":materialCareInfo, :preferredVendor, :priceType, :saleDuration, :saleStatus, :sellingPrice," +
            ":sizeAndFit, :sizeCategory, :sizeMatch, :trackInventory, :unit); SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer createProduct(@Param("brandName") String brandName, @Param("categoryId") Long categoryId,
                   @Param("costPrice") Double costPrice, @Param("createdAt") String createdAt,
                   @Param("createdBy") Long createdBy, @Param("description") String description,
                   @Param("modifiedAt") String modifiedAt, @Param("modifiedBy") Long modifiedBy,
                   @Param("productName") String productName, @Param("price") Double price, @Param("productCode") String productCode,
                   @Param("quantity") Integer quantity, @Param("reorderPoint") Integer reorderPoint,
                   @Param("stockValue") Integer stockValue, @Param("vendorId") Long vendorId, @Param("color") String color,
                   @Param("materialCareInfo") String materialCareInfo, @Param("preferredVendor") String preferredVendor,
                   @Param("priceType") String priceType, @Param("saleDuration") String saleDuration,
                   @Param("saleStatus") String saleStatus, @Param("sellingPrice") Double sellingPrice,
                   @Param("sizeAndFit") String sizeAndFit, @Param("sizeCategory") String sizeCategory,
                   @Param("sizeMatch") boolean sizeMatch, @Param("trackInventory") boolean trackInventory,
                   @Param("unit") String unit);
    
    
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE product SET brand_name = :brandName, category_id = :categoryId, " +
            "cost_price = :costPrice,  description = :description, " +
            "modified_at = :modifiedAt, modified_by = :modifiedBy, name = :productName, price = :price, product_code = :productCode, " +
            "quantity = :quantity, reorder_point = :reorderPoint, stock_value = :stockValue, vendor_id = :vendorId, color = :color," +
            "material_care_info = :materialCareInfo, preferred_vendor = :preferredVendor, price_type = :priceType, sale_duration = :saleDuration, " +
            "sale_status = :saleStatus, selling_price = :sellingPrice, size_and_fit = :sizeAndFit, size_category = :sizeCategory, " +
            "size_match = :sizeMatch, track_inventory = :trackInventory, unit = :unit WHERE id = :productId")
    Integer updateProduct(@Param("productId") Long productId,@Param("brandName") String brandName, @Param("categoryId") Long categoryId,
                          @Param("costPrice") Double costPrice, @Param("description") String description,
                          @Param("modifiedAt") String modifiedAt, @Param("modifiedBy") Long modifiedBy,
                          @Param("productName") String productName, @Param("price") Double price, @Param("productCode") String productCode,
                          @Param("quantity") Integer quantity, @Param("reorderPoint") Integer reorderPoint,
                          @Param("stockValue") Integer stockValue, @Param("vendorId") Long vendorId, @Param("color") String color,
                          @Param("materialCareInfo") String materialCareInfo, @Param("preferredVendor") String preferredVendor,
                          @Param("priceType") String priceType, @Param("saleDuration") String saleDuration,
                          @Param("saleStatus") String saleStatus, @Param("sellingPrice") Double sellingPrice,
                          @Param("sizeAndFit") String sizeAndFit, @Param("sizeCategory") String sizeCategory,
                          @Param("sizeMatch") boolean sizeMatch, @Param("trackInventory") boolean trackInventory,
                          @Param("unit") String unit);

    @Query(value = "SELECT p.id as productId, p.brand_name AS brandName, p.category_id AS categoryId, p.cost_price AS costPrice, p.created_by AS createdBy, p.modified_by as modifiedBy, p.name as name, p.product_code as productCode, p.quantity as quantity, p.reorder_point as reorderPoint, p.stock_value as stockValue, p.vendor_id as vendorId, p.color as color, p.material_care_info as materialCareInfo, p.preferred_vendor as preferredVendor, p.price_type as priceType, p.sale_duration as saleDuration, p.sale_status as saleStatus, p.selling_price as sellingPrice, p.size_and_fit as sizeAndFit, p.size_category as sizeCategory, p.size_match as sizeMatch, p.track_inventory as trackInventory, p.unit, t.description as tagDescription, t.name as tagName, t.id as tagId  FROM product_tag pt left join product_image pi left join product_properties pp left join product p on p.id = pt.product_id left join tag t on pt.tag_name = t.name where pt.product_id = :productId GROUP BY t.name, p.id;", nativeQuery = true)
    List<FindProductResponse> findProductById(@Param("productId") Long productId);

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
