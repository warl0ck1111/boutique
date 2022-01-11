package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.data.repository.RoleRepository;
import com.aneeque.backendservice.dto.request.RoleRequest;
import com.aneeque.backendservice.exception.ApiRequestException;
import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.exception.RoleNotFoundException;
import com.aneeque.backendservice.service.PrivilegeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author B.O Okala III
 */
@Slf4j
@Getter
@Service

public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private PrivilegeService privilegeService;





    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRole(RoleRequest roleRequest) {
        boolean roleExist = roleRepository.existsByName(roleRequest.getName());
        if (roleExist) {
            log.error("Role already exists");
            throw new ApiRequestException("Role already exists", HttpStatus.BAD_REQUEST);
        }
        Role role = new Role(roleRequest.getName());
        return roleRepository.save(role);

    }

    @Transactional
    public String updateRole(Long roleId, RoleRequest req) {
        roleRepository.updateRole(roleId, req.getName(), req.getEntity(), req.getDescription(), LocalDateTime.now().toString());
        return "role update successful";
    }

    public List<Role> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;

    }


    public Role findRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> {
            log.error("role does not exist");
            return new RoleNotFoundException("role does not exist");
        });
        return role;
    }

    public Role findRoleById(Long id) {
        if (id < 1) throw new IllegalArgumentException("invalid role id");

        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("no role found"));
    }

    public Collection<Privilege> getPrivilegesAssignedToRole(Long roleId){
        Role role = findRoleById(roleId);
        return role.getPrivileges();
    }

    public Role assignPermissionsToRole(Long roleId, List<Long> privilegeIds){
        Role role = findRoleById(roleId);
        List<Privilege> privileges = privilegeService.getPrivilegeRepository().findAllById(privilegeIds);
        role.setPrivileges(privileges);
        roleRepository.save(role);

        return role;
    }

}
