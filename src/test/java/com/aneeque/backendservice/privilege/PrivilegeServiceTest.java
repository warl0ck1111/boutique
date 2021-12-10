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
import java.util.Optional;

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
    void testUpdateShouldWhenPrivilegeNameAlreadyExits() {
        Privilege privilege = new Privilege();
        privilege.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setId(123L);
        privilege.setName("Name");
        privilege.setModule("Module");
        privilege.setDescription("The characteristics of someone or something");
        Optional<Privilege> ofResult = Optional.<Privilege>of(privilege);
        when(this.privilegeRepository.findById((Long) any())).thenReturn(ofResult);

        PrivilegeRequest privilegeRequest = new PrivilegeRequest();
        privilegeRequest.setName("Name");
        privilegeRequest.setModule("Module");
        privilegeRequest.setDescription("The characteristics of someone or something");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.privilegeService.update(123L, privilegeRequest));
        String expectedMessage = "privilege Name already exists";
        System.out.println(illegalArgumentException.getMessage());
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.privilegeRepository).findById((Long) any());
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
        PageImpl<Privilege> pageImpl = new PageImpl<Privilege>(new ArrayList<Privilege>());
        when(this.privilegeRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        Page<Privilege> actualAllPrivileges = this.privilegeService.getAllPrivileges(1, 3);
        assertSame(pageImpl, actualAllPrivileges);
        assertTrue(actualAllPrivileges.toList().isEmpty());
        verify(this.privilegeRepository).findAll((org.springframework.data.domain.Pageable) any());
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
    void testFindPrivilegeById() {
        Privilege privilege = new Privilege();
        privilege.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        privilege.setId(123L);
        privilege.setName("Name");
        privilege.setModule("Module");
        privilege.setDescription("The characteristics of someone or something");
        Optional<Privilege> ofResult = Optional.<Privilege>of(privilege);
        when(this.privilegeRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(privilege, this.privilegeService.findPrivilegeById(123L));
        verify(this.privilegeRepository).findById((Long) any());
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

