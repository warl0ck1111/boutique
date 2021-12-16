package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
