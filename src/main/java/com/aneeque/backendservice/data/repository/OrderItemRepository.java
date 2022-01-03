package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.OrderItem;
import com.aneeque.backendservice.dto.response.OrderItemResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "select oi.order_id as orderId, oi.product_id as orderItemProductId, oi.quantity as orderItemQuantity," +
            "       oi.status as orderItemStatus, p.name as productName, p.description as productDescription, " +
            "       p.price as productPrice, o.status as orderStatus, o.delivery_method as orderDeliveryMethod, " +
            "       o.email_address as emailAddress, o.total_amount as orderTotalAmount, o.transaction_ref as orderTransactionRef, " +
            "       o.unique_id as orderUniqueId from order_item oi left join product p on oi.product_id = p.id " +
            "           left join orders o on oi.order_id = o.id where oi.order_id = :orderId", nativeQuery = true)
    List<OrderItemResponseDto> findOrderItemsByOrderId(@Param("orderId") String orderItemId);


}
