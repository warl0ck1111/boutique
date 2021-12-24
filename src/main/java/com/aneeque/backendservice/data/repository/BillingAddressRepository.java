package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {

}
