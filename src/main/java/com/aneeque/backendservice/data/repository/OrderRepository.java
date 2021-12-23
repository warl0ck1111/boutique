package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
