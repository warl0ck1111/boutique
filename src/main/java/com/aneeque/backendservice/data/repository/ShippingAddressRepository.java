package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    List<ShippingAddress> findByEmailAddress(String emailAddress);
}
