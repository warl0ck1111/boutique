package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.OrderItemProductProperties;
import com.aneeque.backendservice.dto.response.OrderItemProductPropertiesResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface OrderItemProductPropertiesRepository extends JpaRepository<OrderItemProductProperties, Long> {


    @Query(value = "select p.name as productName, p.description as productDescription," +
            "       p.price as productPrice, p2.description as propertyDescription, " +
            "p2.data as propertyData  from order_item_product_properties oipp " +
            "left join order_item oi on oipp.order_item_id = oi.id " +
            "left join product p on oipp.product_id = p.id " +
            "left join property p2 on oipp.property_id = p2.id " +
            "where oipp.order_item_id = :orderItemId", nativeQuery = true)
    List<OrderItemProductPropertiesResponseDto> getPropertiesByOrderItemId(@Param("orderItemId") Long orderItemId);
}
