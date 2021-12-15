package com.aneeque.backendservice.category;

import com.aneeque.backendservice.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Okala Bashir .O.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
