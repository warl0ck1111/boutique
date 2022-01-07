package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Modifying
    @Query(value = "UPDATE brand b SET b.name = :name WHERE b.id = :brandId", nativeQuery = true)
    Integer updateBrand(@Param("brandId") Long brandId, @Param("name") String name);

    @Modifying
    @Query(value = "DELETE FROM brand WHERE id = :brandId", nativeQuery = true)
    void deleteByTagId(@Param("brandId") Long brandId);
}
