package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCreatorId(Long creatorId);

    Optional<Cart> findByUniqueId(String uniqueId);
}
