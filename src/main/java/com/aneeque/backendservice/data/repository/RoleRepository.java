package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.dto.response.NoOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    Integer updateRole(@Param("roleId") Long roleId, @Param("roleName") String roleName, @Param("entity") String entity, @Param("description") String description, @Param("modifiedAt") String modifiedAt);


    @Query(value = "SELECT a.role_id as roleId, count(*) as noOfUsers FROM app_user a WHERE a.role_id = :roleId GROUP BY a.role_id", nativeQuery = true)
    Optional<NoOfUser> countNoOfUsersRoleHas(@Param("roleId") Long roleId);

    @Query(value = "SELECT a.role_id as roleId, count(*) as noOfUsers FROM app_user a GROUP BY a.role_id", nativeQuery = true)
    List<NoOfUser> countNoOfUsersAllRoleHave();

    @Modifying
    @Query(value = "DELETE FROM role_privileges WHERE role_id = :roleId AND privileges_id = :privilegeId", nativeQuery = true)
    void unAssignPermissionsFromRole(@Param("roleId") Long roleId, @Param("privilegeId") Long privilegeId);
}




