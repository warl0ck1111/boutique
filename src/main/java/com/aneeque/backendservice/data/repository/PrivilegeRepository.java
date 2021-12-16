package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String authority);


    boolean existsByName(String name);
}
