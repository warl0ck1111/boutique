package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.dto.response.RoleNoOfUsers;
import com.aneeque.backendservice.data.repository.RoleRepository;
import com.aneeque.backendservice.dto.request.RoleRequest;
import com.aneeque.backendservice.exception.ApiRequestException;
import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.exception.RoleNotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        List<Privilege> privileges = privilegeService.getPrivilegeRepository().findAllById(roleRequest.getPermissions());
        role.setPrivileges(privileges);
        return roleRepository.save(role);

    }

    @Transactional
    public String updateRole(Long roleId, RoleRequest req) {
        Role role = findRoleById(roleId);
        role.setDescription(req.getDescription());
        role.setName(req.getName());
        if (req.isUpdatePrivileges()) {
            List<Privilege> privileges = privilegeService.getPrivilegeRepository().findAllById(req.getPermissions());
            role.setPrivileges(privileges);
        }
        roleRepository.save(role);
        return "role update successful";
    }

    public List<Role> findAllRoles() {
        List<RoleNoOfUsers> roleNoOfUsers = countNoOfUsersAllRolesHave();
        List<Role> roles = roleRepository.findAll().stream().map(x -> {
            roleNoOfUsers.forEach(y -> {
                if (y.getRoleId() == x.getId()) {
                    x.setNoOfUsers(y.getNoOfUsers());
                }
            });
            return x;
        }).collect(Collectors.toList());

        return roles;
    }


    public Role findRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> {
            log.error("role does not exist");
            return new RoleNotFoundException("role does not exist");
        });
        return role;
    }

    public Role findRoleById(Long roleId) {
        if (roleId < 1) throw new IllegalArgumentException("invalid role id");
        RoleNoOfUsers roleNoOfUsers = countNoOfUsersARoleHas(roleId);
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("no role found"));
        role.setNoOfUsers(roleNoOfUsers.getNoOfUsers());
        return role;
    }

    private RoleNoOfUsers countNoOfUsersARoleHas(Long roleId) {
        return roleRepository.countNoOfUsersRoleHas(roleId).orElseThrow(() -> new IllegalArgumentException("invalid role id"));
    }

    private List<RoleNoOfUsers> countNoOfUsersAllRolesHave() {
        return roleRepository.countNoOfUsersAllRoleHave();
    }

    public Role getPrivilegesAssignedToRole(Long roleId) {
        Role role = findRoleById(roleId);
        return role;
    }

    @Transactional
    public Role updateRolePermissions(Long roleId, List<Long> privilegeIds) {
        Role role = findRoleById(roleId);
        List<Privilege> privileges = privilegeService.getPrivilegeRepository().findAllById(privilegeIds);
        role.setPrivileges(privileges);
        roleRepository.save(role);

        return role;
    }


}
