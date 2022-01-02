package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Order;
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
}
