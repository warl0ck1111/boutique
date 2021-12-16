package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.data.repository.RoleRepository;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Role createRole(String roleName) {
        boolean roleExist = roleRepository.existsByName(roleName);
        if (roleExist) {
            log.error("Role already exists");
            throw new ApiRequestException("Role already exists", HttpStatus.BAD_REQUEST);
        }
        Role role = new Role(roleName);
        return roleRepository.save(role);

    }

    public List<Role> findAllRoles() {
        List<Role> roleList = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            String roleName = role.getName().replace("_", " ");
            roleList.add(new Role(role.getId(), roleName));
        }
        return roleList;
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
