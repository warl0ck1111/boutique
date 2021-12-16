package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author B.O Okala III
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);

    boolean existsByName(String roleName);
}
