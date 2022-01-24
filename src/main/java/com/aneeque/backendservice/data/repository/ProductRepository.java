package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.dto.response.FindAllProductResponse;
import com.aneeque.backendservice.dto.response.FindProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT id,brand_name, cost_price, created_at, created_by, description, modified_at," +
            "                                 modified_by, name, price, product_code, quantity, reorder_point, stock_value, vendor_id, " +
            "                                 material_care_info, price_type, sale_duration, sale_status, selling_price," +
            "                                 track_inventory from product ", nativeQuery = true)
    List<Product> findAllProducts(Pageable pageable);

    @Modifying
    @Query(value = "insert into product (brand_name, cost_price, created_at, created_by, description, modified_at," +
            "                     modified_by, name, price, product_code, quantity, reorder_point, stock_value, vendor_id," +
            "                     material_care_info, price_type, sale_duration, sale_status, selling_price," +
            "                      track_inventory)" +
            "values (:brandName,  :costPrice, :createdAt, :createdBy, :description,:modifiedAt," +
            ":modifiedBy, :productName, :price, :productCode, :quantity, :reorderPoint, :stockValue, :vendorId," +
            ":materialCareInfo,  :priceType, :saleDuration, :saleStatus, :sellingPrice," +
            ":trackInventory); SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer createProduct(@Param("brandName") String brandName,
                          @Param("costPrice") Double costPrice, @Param("description") String description,
                          @Param("modifiedAt") String modifiedAt, @Param("modifiedBy") Long modifiedBy,
                          @Param("productName") String productName, @Param("price") Double price, @Param("productCode") String productCode,
                          @Param("quantity") Integer quantity, @Param("reorderPoint") Integer reorderPoint,
                          @Param("stockValue") Integer stockValue, @Param("vendorId") Long vendorId,
                          @Param("materialCareInfo") String materialCareInfo,
                          @Param("priceType") String priceType, @Param("saleDuration") String saleDuration,
                          @Param("saleStatus") String saleStatus, @Param("sellingPrice") Double sellingPrice,
                          @Param("trackInventory") boolean trackInventory);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE product SET brand_name = :brandName,  " +
            "cost_price = :costPrice,  description = :description, " +
            "modified_at = :modifiedAt, modified_by = :modifiedBy, name = :productName, price = :price, product_code = :productCode, " +
            "quantity = :quantity, reorder_point = :reorderPoint, stock_value = :stockValue, vendor_id = :vendorId, " +
            "material_care_info = :materialCareInfo, price_type = :priceType, sale_duration = :saleDuration, " +
            "sale_status = :saleStatus, selling_price = :sellingPrice,  " +
            " track_inventory = :trackInventory WHERE id = :productId")
    Integer updateProduct(@Param("productId") Long productId, @Param("brandName") String brandName,
                          @Param("costPrice") Double costPrice, @Param("description") String description,
                          @Param("modifiedAt") String modifiedAt, @Param("modifiedBy") Long modifiedBy,
                          @Param("productName") String productName, @Param("price") Double price, @Param("productCode") String productCode,
                          @Param("quantity") Integer quantity, @Param("reorderPoint") Integer reorderPoint,
                          @Param("stockValue") Integer stockValue, @Param("vendorId") Long vendorId,
                          @Param("materialCareInfo") String materialCareInfo,
                          @Param("priceType") String priceType, @Param("saleDuration") String saleDuration,
                          @Param("saleStatus") String saleStatus, @Param("sellingPrice") Double sellingPrice,
                          @Param("trackInventory") boolean trackInventory);

    @Query(value = "SELECT DISTINCTROW p.id as productId, p.brand_name AS brandName, " +
            "p.cost_price AS costPrice, p.created_by AS createdBy, p.modified_by as modifiedBy, " +
            "p.name as name, p.price, p.description, p.product_code as productCode, p.quantity as quantity, " +
            "p.reorder_point as reorderPoint, p.stock_value as stockValue, p.vendor_id as vendorId, " +
            "p.material_care_info as materialCareInfo," +
            "p.price_type as priceType, p.sale_duration as saleDuration, p.sale_status as saleStatus, " +
            "p.selling_price as sellingPrice, p.created_at AS createdAt, pi.file_name as fileName, pi.file_type as fileType, " +
            " p.track_inventory as trackInventory," +
            " pt.tag_name AS tagName, pc.category_id as categoryId  FROM product p left join product_tag pt on p.id = pt.product_id left join product_media pi on p.id = pi.product_id " +
            "left join product_categories pc on p.id = pc.product_id  where pt.product_id = :productId ", nativeQuery = true)
    List<FindProductResponse> findProductById(@Param("productId") Long productId);

    @Modifying
    @Query(value = "DELETE FROM product where id=:productId", nativeQuery = true)
    Integer deleteProductById(@Param("productId") Long productId);


    Page<Product> findByNameContainingIgnoreCase(String productName, Pageable pageable);


    @Modifying
    @Query(value = "INSERT INTO product_properties (product_id, property_id, price ) value (:productId, :propertyId, :price)", nativeQuery = true)
    Integer addProductProperty(@Param("productId") Long productId, @Param("propertyId") Long propertyId, @Param("price") Double price);

    @Modifying
    @Query(value = "DELETE FROM product_properties WHERE product_id = :productId AND property_id = :propertyId", nativeQuery = true)
    Integer removeProductProperty(@Param("productId") Long productId, @Param("propertyId") Long propertyId);

    @Modifying
    @Query(value = "INSERT INTO category_product_properties (category_id,product_id, property_id ) value (:categoryId,:productId, :propertyId)", nativeQuery = true)
    Integer addCategoryProductProperty(@Param("categoryId") Long categoryId, @Param("productId") Long productId, @Param("propertyId") Long propertyId);

    @Modifying
    @Query(value = "DELETE FROM category_product_properties WHERE product_id = :productId", nativeQuery = true)
    Integer removeCategoryProductProperties(@Param("productId") Long productId);


    @Modifying
    @Query(value = "INSERT INTO product_categories(product_id, category_id) VALUES (:productId, :categoryId)", nativeQuery = true)
    Integer addProductCategory(@Param("productId") Long productId, @Param("categoryId") Long categoryId);

    @Modifying
    @Query(value = "DELETE FROM product_categories WHERE product_id = :productId", nativeQuery = true)
    Integer removeProductCategories(@Param("productId") Long productId);


    @Query(value = "SELECT p.id AS productId, p.cost_price AS costPrice," +
            "            p.name AS name, p.price, p.description, p.stock_value AS stockValue, p.created_at AS createdAt," +
            "            p.selling_price AS sellingPrice, pt.tag_name AS tagName, pc.category_id AS categoryId, " +
            "            c.name AS categoryName, pm.file_name AS fileName, " +
            "            pm.file_type AS fileType , p.quantity, p.product_code AS productCode" +
            "            FROM product p left join product_tag pt ON p.id = pt.product_id " +
            "            left join product_categories pc ON p.id = pc.product_id " +
            "            left join category c ON pc.category_id = c.id  " +
            "            left join product_media pm ON p.id = pm.product_id order by p.id desc ", nativeQuery = true)
    List<FindAllProductResponse> findAllProduct();
}
