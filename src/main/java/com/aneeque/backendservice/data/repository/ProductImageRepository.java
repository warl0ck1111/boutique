package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.data.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {

    List<ProductImage> findAllByProduct(Product product);
}
