package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.AppUser;
import com.aneeque.backendservice.data.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author B.O Okala III
 */
@Transactional(readOnly = true)
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmailAddress(String email);

    boolean existsByEmailAddress(String email);


    Page<AppUser> findAllByRole(Role role, Pageable pageable);

    @Query(value = "select a from AppUser a where a.emailAddress like '%:query%'")
    Page<AppUser> searchAppUserByEmail(@Param(value = "query") String email, Pageable pageable);

    boolean existsByMobileNumber(String mobileNumber);

}
