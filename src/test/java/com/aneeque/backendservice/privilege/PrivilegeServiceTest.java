package com.aneeque.backendservice.privilege;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.data.repository.PrivilegeRepository;
import com.aneeque.backendservice.dto.request.PrivilegeRequest;
import com.aneeque.backendservice.dto.response.PrivilegeNoOfRoles;
import com.aneeque.backendservice.exception.PrivilegeNotFoundException;
import com.aneeque.backendservice.service.PrivilegeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PrivilegeService.class})
@ExtendWith(SpringExtension.class)
class PrivilegeServiceTest {
    @MockBean
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    void testCreateShouldFailIfPrivilegeAlreadyExists() {
        when(this.privilegeRepository.existsByName((String) any())).thenReturn(true);

        PrivilegeRequest privilegeRequest = new PrivilegeRequest();
        privilegeRequest.setName("Name");
        privilegeRequest.setModule("Module");
        privilegeRequest.setDescription("The characteristics of someone or something");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.privilegeService.create(privilegeRequest));
        String expectedMessage = "privilege already exists";
        System.out.println(illegalArgumentException.getMessage());
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.privilegeRepository).existsByName((String) any());
    }

    @Test
    void testCreate2() {
        Privilege privilege = new Privilege();
        privilege.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setId(123L);
        privilege.setName("Name");
        privilege.setModule("Module");
        privilege.setDescription("The characteristics of someone or something");
        when(this.privilegeRepository.save((Privilege) any())).thenReturn(privilege);
        when(this.privilegeRepository.existsByName((String) any())).thenReturn(false);

        PrivilegeRequest privilegeRequest = new PrivilegeRequest();
        privilegeRequest.setName("Name");
        privilegeRequest.setModule("Module");
        privilegeRequest.setDescription("The characteristics of someone or something");
        assertSame(privilege, this.privilegeService.create(privilegeRequest));
        verify(this.privilegeRepository).existsByName((String) any());
        verify(this.privilegeRepository).save((Privilege) any());
    }


    @Test
    void testUpdateShouldFailIfPrivilegeIdDoesNotExist() {
        when(this.privilegeRepository.findById((Long) any())).thenReturn(Optional.<Privilege>empty());

        PrivilegeRequest privilegeRequest = new PrivilegeRequest();
        privilegeRequest.setName("Name");
        privilegeRequest.setModule("Module");
        privilegeRequest.setDescription("The characteristics of someone or something");
        PrivilegeNotFoundException privilegeNotFoundException = assertThrows(PrivilegeNotFoundException.class, () -> this.privilegeService.update(123L, privilegeRequest));
        String expectedMessage = "no privilege found";
        System.out.println(privilegeNotFoundException.getMessage());
        String actualMessage = privilegeNotFoundException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.privilegeRepository).findById((Long) any());
    }

    @Test
    void testGetAllPrivileges() {
        when(this.privilegeRepository.findAll()).thenReturn(new ArrayList<Privilege>());
        when(this.privilegeRepository.countNoOfRolesAllPrivilegeHas()).thenReturn(new ArrayList<PrivilegeNoOfRoles>());
        assertTrue(this.privilegeService.getAllPrivileges().isEmpty());
        verify(this.privilegeRepository).countNoOfRolesAllPrivilegeHas();
        verify(this.privilegeRepository).findAll();
    }
    @Test
    void testDelete() {
        doNothing().when(this.privilegeRepository).deleteById((Long) any());
        this.privilegeService.delete(123L);
        verify(this.privilegeRepository).deleteById((Long) any());
    }

    @Test
    void testFindPrivilegeByName() {
        Privilege privilege = new Privilege();
        privilege.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setId(123L);
        privilege.setName("Name");
        privilege.setModule("Module");
        privilege.setDescription("The characteristics of someone or something");
        Optional<Privilege> ofResult = Optional.<Privilege>of(privilege);
        when(this.privilegeRepository.findByName((String) any())).thenReturn(ofResult);
        assertSame(privilege, this.privilegeService.findPrivilegeByName("Name"));
        verify(this.privilegeRepository).findByName((String) any());
    }

    @Test
    void testFindPrivilegeByNameShouldFailWhenPrivilegeNameDoesNotExits() {
        when(this.privilegeRepository.findByName((String) any())).thenReturn(Optional.<Privilege>empty());
        PrivilegeNotFoundException privilegeNotFoundException = assertThrows(PrivilegeNotFoundException.class, () -> this.privilegeService.findPrivilegeByName("Name"));
        String expectedMessage = "no privilege found";
        System.out.println(privilegeNotFoundException.getMessage());
        String actualMessage = privilegeNotFoundException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.privilegeRepository).findByName((String) any());
    }


    @Test
    void testFindPrivilegeByIdShouldFailWhenPrivilegeIdDoesNotExist() {
        when(this.privilegeRepository.findById((Long) any())).thenReturn(Optional.<Privilege>empty());
        PrivilegeNotFoundException privilegeNotFoundException = assertThrows(PrivilegeNotFoundException.class, () -> this.privilegeService.findPrivilegeById(123L));
        String expectedMessage = "no privilege found";
        System.out.println(privilegeNotFoundException.getMessage());
        String actualMessage = privilegeNotFoundException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.privilegeRepository).findById((Long) any());
    }
}

