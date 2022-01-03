package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.dto.response.CartProductPropertiesDto;
import com.aneeque.backendservice.data.entity.CartProductProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface CartProductPropertiesRepository extends JpaRepository<CartProductProperties, Long> {


    @Query(value = "select  cpp.cart_id as cartId, cpp.properties_id as productPropertyId, cpp.product_id as productId, " +
            "p.name as productName, p.description as productDescription, p.price as productPrice, " +
            "c.quantity as cartQuantity, c.unique_id as cartUniqueId, p2.`data` as productPropertyData, " +
            "p2.description as productPropertyDescription  from cart_product_properties cpp " +
            "left join cart c on cpp.cart_id = c.id left join product p on cpp.product_id = p.id " +
            "left join property p2 on cpp.properties_id = p2.id where cpp.cart_id = :cartId", nativeQuery = true)
    List<CartProductPropertiesDto> getPropertyByCartId(@Param("cartId") Long cartId);



}
