package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author B.O Okala III
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);

    boolean existsByName(String roleName);

    @Modifying
    @Query(value = "UPDATE role r SET r.name =:roleName, r.entity = :entity, r.description = :description, r.modified_at = :modifiedAt WHERE r.id = :roleId", nativeQuery = true)
    Integer updateRole(@Param("roleId")Long roleId, @Param("roleName")String roleName, @Param("entity")String entity, @Param("description")String description,@Param("modifiedAt")String modifiedAt);
}
