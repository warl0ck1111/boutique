package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Order;
import com.aneeque.backendservice.dto.response.OrderItemResponseDto;
import com.aneeque.backendservice.dto.response.OrderResponseDTO;
import com.aneeque.backendservice.dto.response.OrderResponseDtoInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = " UPDATE Orders set status = :orderId", nativeQuery = true)
    void updateOrderStatus(@Param("orderId") Long orderId);


    List<Order> findByEmailAddress(String emailAddress);

    @Query(value = "select o.id, o.unique_id as uniqueId, o.created_at as createdAt, o.email_address as emailAddress, " +
            "o.delivery_method as deliveryMethod, o.payment_method as paymentMethod, o.status, o.total_amount as totalAmount, " +
            "o.transaction_ref as transactionRef, concat(au.first_name, ' ', au.last_name) as customerName from orders o " +
            " left join app_user au on au.email_address = o.email_address ", nativeQuery = true)
    List<OrderResponseDtoInterface> getAllOrders();


}
