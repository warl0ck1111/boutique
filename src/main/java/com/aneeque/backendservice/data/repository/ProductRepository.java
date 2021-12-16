package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
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
