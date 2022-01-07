package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Modifying
    @Query(value = "update tag t set t.name=:name, t.slug = :slug, t.description = :description where t.id=:tagId", nativeQuery = true)
    int updateTag(@Param("tagId") Long tagId, @Param("slug") String slug , @Param("name") String name , @Param("description") String description);
}
