package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.dto.request.PrivilegeListRequest;
import com.aneeque.backendservice.dto.request.RoleRequest;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author B.O Okala III
 */

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getRoles() {
        List<Role> roles = roleService.findAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(path = "{roleId}/get-all/privileges")
    public ResponseEntity<?> getPrivilegesAssignedToRole(@PathVariable Long roleId) {
        Collection<Privilege> privileges = roleService.getPrivilegesAssignedToRole(roleId);
        return ResponseEntity.ok(new ApiResponse(privileges));
    }

    @PostMapping("{roleId}/assign-permission")
    public ResponseEntity<?> assignPermissionsToRole(@PathVariable Long roleId, @RequestBody PrivilegeListRequest privilegeListRequest) {
        Role role = roleService.assignPermissionsToRole(roleId, privilegeListRequest.getPrivileges());
        return ResponseEntity.ok(new ApiResponse(role));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleService.createRole(roleRequest);
        return ResponseEntity.ok(new ApiResponse(role));
    }
 @PutMapping("{roleId}")
    public ResponseEntity<ApiResponse> updateRole(@PathVariable String roleId ,@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(new ApiResponse(roleService.updateRole( Long.valueOf(roleId),roleRequest)));
    }

}
