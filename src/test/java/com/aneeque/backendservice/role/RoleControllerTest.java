package com.aneeque.backendservice.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.privilege.Privilege;
import com.aneeque.backendservice.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleService roleService;

    @Test
    void testGetPrivilegesAssignedToRole() {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleRepository roleRepository = mock(RoleRepository.class);
        when(roleRepository.findById((Long) any())).thenReturn(Optional.<Role>of(role));
        ResponseEntity<?> actualPrivilegesAssignedToRole = (new RoleController(new RoleService(roleRepository)))
                .getPrivilegesAssignedToRole(123L);
        assertTrue(actualPrivilegesAssignedToRole.hasBody());
        assertTrue(actualPrivilegesAssignedToRole.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualPrivilegesAssignedToRole.getStatusCode());
        assertEquals(200, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getHttpStatus());
        assertTrue(((Collection<Object>) ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getData()).isEmpty());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getSuccess());
        verify(roleRepository).findById((Long) any());
    }

    @Test
    void testGetPrivilegesAssignedToRole2() {
        RoleService roleService = mock(RoleService.class);
        when(roleService.getPrivilegesAssignedToRole((Long) any())).thenReturn(new ArrayList<Privilege>());
        ResponseEntity<?> actualPrivilegesAssignedToRole = (new RoleController(roleService))
                .getPrivilegesAssignedToRole(123L);
        assertTrue(actualPrivilegesAssignedToRole.hasBody());
        assertTrue(actualPrivilegesAssignedToRole.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualPrivilegesAssignedToRole.getStatusCode());
        assertEquals(200, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getHttpStatus());
        assertTrue(((Collection<Object>) ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getData()).isEmpty());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualPrivilegesAssignedToRole.getBody()).getSuccess());
        verify(roleService).getPrivilegesAssignedToRole((Long) any());
    }

    @Test
    void testAssignPermissionsToRole() {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleService roleService = mock(RoleService.class);
        when(roleService.assignPermissionsToRole((Long) any(), (java.util.List<Long>) any())).thenReturn(role);
        RoleController roleController = new RoleController(roleService);

        PrivilegeListRequest privilegeListRequest = new PrivilegeListRequest();
        privilegeListRequest.setPrivileges(new ArrayList<Long>());
        ResponseEntity<?> actualAssignPermissionsToRoleResult = roleController.assignPermissionsToRole(123L,
                privilegeListRequest);
        assertTrue(actualAssignPermissionsToRoleResult.hasBody());
        assertTrue(actualAssignPermissionsToRoleResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAssignPermissionsToRoleResult.getStatusCode());
        assertEquals(200, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getHttpStatus());
        assertSame(role, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getData());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getSuccess());
        verify(roleService).assignPermissionsToRole((Long) any(), (java.util.List<Long>) any());
    }

    @Test
    void testAssignPermissionsToRole2() {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleService roleService = mock(RoleService.class);
        when(roleService.assignPermissionsToRole((Long) any(), (java.util.List<Long>) any())).thenReturn(role);
        RoleController roleController = new RoleController(roleService);

        PrivilegeListRequest privilegeListRequest = new PrivilegeListRequest();
        privilegeListRequest.setPrivileges(new ArrayList<Long>());
        ResponseEntity<?> actualAssignPermissionsToRoleResult = roleController.assignPermissionsToRole(123L,
                privilegeListRequest);
        assertTrue(actualAssignPermissionsToRoleResult.hasBody());
        assertTrue(actualAssignPermissionsToRoleResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAssignPermissionsToRoleResult.getStatusCode());
        assertEquals(200, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getHttpStatus());
        assertSame(role, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getData());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualAssignPermissionsToRoleResult.getBody()).getSuccess());
        verify(roleService).assignPermissionsToRole((Long) any(), (java.util.List<Long>) any());
    }

    @Test
    void testGetRoles() throws Exception {
        when(this.roleService.findAllRoles()).thenReturn(new ArrayList<Role>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles/get-all");
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetRoles2() throws Exception {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("?");
        role.setEntity("?");
        role.setDescription("The characteristics of someone or something");

        ArrayList<Role> roleList = new ArrayList<Role>();
        roleList.add(role);
        when(this.roleService.findAllRoles()).thenReturn(roleList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles/get-all");
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"description\":\"The characteristics of someone or something\",\"entity\":\"?\"}]"));
    }
}

