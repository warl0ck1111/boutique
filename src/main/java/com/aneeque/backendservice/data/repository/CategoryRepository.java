package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryType(CategoryType categoryType);

}
