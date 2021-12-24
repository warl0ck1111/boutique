package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Okala Bashir .O.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
