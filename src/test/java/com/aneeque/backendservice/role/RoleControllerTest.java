package com.aneeque.backendservice.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.controller.RoleController;
import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.data.entity.Role;
import com.aneeque.backendservice.data.repository.RoleRepository;
import com.aneeque.backendservice.dto.request.PrivilegeListRequest;
import com.aneeque.backendservice.dto.request.RoleRequest;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.dto.response.RoleNoOfUsers;
import com.aneeque.backendservice.service.RoleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    void testGetPrivilegesAssignedToRoleById() {
        RoleNoOfUsers roleNoOfUsers = mock(RoleNoOfUsers.class);
        when(roleNoOfUsers.getNoOfUsers()).thenReturn(1L);
        Optional<RoleNoOfUsers> ofResult = Optional.<RoleNoOfUsers>of(roleNoOfUsers);

        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleRepository roleRepository = mock(RoleRepository.class);
        when(roleRepository.findById((Long) any())).thenReturn(Optional.<Role>of(role));
        when(roleRepository.countNoOfUsersRoleHas((Long) any())).thenReturn(ofResult);
        ResponseEntity<?> actualPrivilegesAssignedToRoleById = (new RoleController(new RoleService(roleRepository)))
                .getPrivilegesAssignedToRoleById(123L);
        assertTrue(actualPrivilegesAssignedToRoleById.hasBody());
        assertTrue(actualPrivilegesAssignedToRoleById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualPrivilegesAssignedToRoleById.getStatusCode());
        assertEquals(200, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getHttpStatus());
        Object data = ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getData();
        assertSame(role, data);
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getSuccess());
        assertEquals(1L, ((Role) data).getNoOfUsers().longValue());
        verify(roleRepository).countNoOfUsersRoleHas((Long) any());
        verify(roleRepository).findById((Long) any());
        verify(roleNoOfUsers).getNoOfUsers();
    }

    @Test
    void testGetPrivilegesAssignedToRoleById2() {
        RoleNoOfUsers roleNoOfUsers = mock(RoleNoOfUsers.class);
        when(roleNoOfUsers.getNoOfUsers()).thenReturn(1L);
        Optional.<RoleNoOfUsers>of(roleNoOfUsers);

        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleService roleService = mock(RoleService.class);
        when(roleService.getPrivilegesAssignedToRole((Long) any())).thenReturn(role);
        ResponseEntity<?> actualPrivilegesAssignedToRoleById = (new RoleController(roleService))
                .getPrivilegesAssignedToRoleById(123L);
        assertTrue(actualPrivilegesAssignedToRoleById.hasBody());
        assertTrue(actualPrivilegesAssignedToRoleById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualPrivilegesAssignedToRoleById.getStatusCode());
        assertEquals(200, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getStatusCode());
        assertEquals("Success", ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getHttpStatus());
        assertSame(role, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getData());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualPrivilegesAssignedToRoleById.getBody()).getSuccess());
        verify(roleService).getPrivilegesAssignedToRole((Long) any());
    }

    @Test
    void testAssignPermissionsToRole() {
        RoleNoOfUsers roleNoOfUsers = mock(RoleNoOfUsers.class);
        when(roleNoOfUsers.getNoOfUsers()).thenReturn(1L);
        Optional.<RoleNoOfUsers>of(roleNoOfUsers);

        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleService roleService = mock(RoleService.class);
        when(roleService.updateRolePermissions((Long) any(), (java.util.List<Long>) any())).thenReturn(role);
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
        verify(roleService).updateRolePermissions((Long) any(), (java.util.List<Long>) any());
    }

    @Test
    void testUnassignPermissionsFromRole() {
        RoleController roleController = new RoleController(new RoleService(mock(RoleRepository.class)));

        PrivilegeListRequest privilegeListRequest = new PrivilegeListRequest();
        privilegeListRequest.setPrivileges(new ArrayList<Long>());
        ResponseEntity<?> actualUnassignPermissionsFromRoleResult = roleController.updateRolePermissions(123L,
                privilegeListRequest);
        assertTrue(actualUnassignPermissionsFromRoleResult.hasBody());
        assertTrue(actualUnassignPermissionsFromRoleResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUnassignPermissionsFromRoleResult.getStatusCode());
        assertEquals(200, ((ApiResponse) actualUnassignPermissionsFromRoleResult.getBody()).getStatusCode());
        assertEquals("privileges unassigned successfully",
                ((ApiResponse) actualUnassignPermissionsFromRoleResult.getBody()).getMessage());
        assertEquals(HttpStatus.OK, ((ApiResponse) actualUnassignPermissionsFromRoleResult.getBody()).getHttpStatus());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, ((ApiResponse) actualUnassignPermissionsFromRoleResult.getBody()).getSuccess());
    }



    @Test
    void testCreateRole() {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleRepository roleRepository = mock(RoleRepository.class);
        when(roleRepository.save((Role) any())).thenReturn(role);
        when(roleRepository.existsByName((String) any())).thenReturn(false);
        RoleController roleController = new RoleController(new RoleService(roleRepository));
        ResponseEntity<ApiResponse> actualCreateRoleResult = roleController
                .createRole(new RoleRequest("Name", "The characteristics of someone or something", "Entity", false, new ArrayList<>()));
        assertTrue(actualCreateRoleResult.getHeaders().isEmpty());
        assertTrue(actualCreateRoleResult.hasBody());
        assertEquals(HttpStatus.OK, actualCreateRoleResult.getStatusCode());
        ApiResponse body = actualCreateRoleResult.getBody();
        assertEquals("Success", body.getMessage());
        assertEquals(HttpStatus.OK, body.getHttpStatus());
        assertSame(role, body.getData());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, body.getSuccess());
        assertEquals(200, body.getStatusCode());
        verify(roleRepository).existsByName((String) any());
        verify(roleRepository).save((Role) any());
    }

    @Test
    void testCreateRole2() {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        RoleService roleService = mock(RoleService.class);
        when(roleService.createRole((RoleRequest) any())).thenReturn(role);
        RoleController roleController = new RoleController(roleService);
        ResponseEntity<ApiResponse> actualCreateRoleResult = roleController
                .createRole(new RoleRequest("Name", "The characteristics of someone or something", "Entity", false, new ArrayList<>()));
        assertTrue(actualCreateRoleResult.getHeaders().isEmpty());
        assertTrue(actualCreateRoleResult.hasBody());
        assertEquals(HttpStatus.OK, actualCreateRoleResult.getStatusCode());
        ApiResponse body = actualCreateRoleResult.getBody();
        assertEquals("Success", body.getMessage());
        assertEquals(HttpStatus.OK, body.getHttpStatus());
        assertSame(role, body.getData());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, body.getSuccess());
        assertEquals(200, body.getStatusCode());
        verify(roleService).createRole((RoleRequest) any());
    }

    @Test
    void testUpdateRole() {
        RoleRepository roleRepository = mock(RoleRepository.class);
        when(roleRepository.updateRole((Long) any(), (String) any(), (String) any(), (String) any(), (String) any()))
                .thenReturn(1);
        RoleController roleController = new RoleController(new RoleService(roleRepository));
        ResponseEntity<ApiResponse> actualUpdateRoleResult = roleController.updateRole("1",
                new RoleRequest("Name", "The characteristics of someone or something", "Entity", false, new ArrayList<Long>()));
        assertTrue(actualUpdateRoleResult.getHeaders().isEmpty());
        assertTrue(actualUpdateRoleResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateRoleResult.getStatusCode());
        ApiResponse body = actualUpdateRoleResult.getBody();
        assertEquals("role update successful", body.getMessage());
        assertEquals(HttpStatus.OK, body.getHttpStatus());
        assertEquals(200, body.getStatusCode());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, body.getSuccess());
        verify(roleRepository).updateRole((Long) any(), (String) any(), (String) any(), (String) any(), (String) any());
    }

    @Test
    void testUpdateRole2() {
        RoleService roleService = mock(RoleService.class);
        when(roleService.updateRole((Long) any(), (RoleRequest) any())).thenReturn("2020-03-01");
        RoleController roleController = new RoleController(roleService);
        ResponseEntity<ApiResponse> actualUpdateRoleResult = roleController.updateRole("42",
                new RoleRequest("Name", "The characteristics of someone or something", "Entity", false, new ArrayList<>()));
        assertTrue(actualUpdateRoleResult.getHeaders().isEmpty());
        assertTrue(actualUpdateRoleResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateRoleResult.getStatusCode());
        ApiResponse body = actualUpdateRoleResult.getBody();
        assertEquals("2020-03-01", body.getMessage());
        assertEquals(HttpStatus.OK, body.getHttpStatus());
        assertEquals(200, body.getStatusCode());
        String expectedSuccess = Boolean.TRUE.toString();
        assertEquals(expectedSuccess, body.getSuccess());
        verify(roleService).updateRole((Long) any(), (RoleRequest) any());
    }

    @Test
    void testGetAllRoles() throws Exception {
        when(this.roleService.findAllRoles()).thenReturn(new ArrayList<Role>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles");
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllRoles2() throws Exception {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(0L);
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles");
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"description\":\"The characteristics of someone or something\",\"entity\":\"?\",\"noOfUsers"
                                        + "\":0,\"privileges\":[],\"createdAt\":[1,1,1,1,1]}]"));
    }

    @Test
    void testGetRoleById() throws Exception {
        Role role = new Role();
        role.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setNoOfUsers(1L);
        role.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setIsDeleted(true);
        role.setPrivileges(new ArrayList<Privilege>());
        role.setId(123L);
        role.setName("Name");
        role.setEntity("Entity");
        role.setDescription("The characteristics of someone or something");
        when(this.roleService.findRoleById((Long) any())).thenReturn(role);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles/{roleId}", 123L);
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"entity\":\"Entity"
                                        + "\",\"noOfUsers\":1,\"privileges\":[],\"createdAt\":[1,1,1,1,1]}"));
    }
}

