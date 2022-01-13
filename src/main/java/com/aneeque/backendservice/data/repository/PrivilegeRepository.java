package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.dto.response.PrivilegeNoOfRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String authority);


    boolean existsByName(String name);


    @Query(value = "SELECT r.privileges_id as privilegeId, count(*) as noOfRoles FROM role_privileges r where r.privileges_id = :privilegeId GROUP BY r.privileges_id;", nativeQuery = true)
    Optional<PrivilegeNoOfRoles> countNoOfRolesAPrivilegeHas(@Param("privilegeId") Long privilegeId);

    @Query(value = "SELECT r.privileges_id as privilegeId, count(*) as noOfRoles FROM role_privileges r GROUP BY r.privileges_id;", nativeQuery = true)
    List<PrivilegeNoOfRoles> countNoOfRolesAllPrivilegeHas();

}
