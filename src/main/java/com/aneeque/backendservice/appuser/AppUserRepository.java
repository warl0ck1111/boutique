package com.aneeque.backendservice.appuser;

import com.aneeque.backendservice.role.Role;
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

    @Query(value = "select a.* from app_user a where a.email like '%:query%';", nativeQuery = true)
    Page<AppUser> searchAppUserEmail(@Param(value = "query") String email, Pageable pageable);

    boolean existsByMobileNumber(String mobileNumber);

}
