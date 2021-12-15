package com.aneeque.backendservice.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPropertiesIn(List<Long>propertyIds );
}
