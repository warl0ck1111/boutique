package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Okala Bashir .O.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
