package com.aneeque.backendservice.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.aneeque.backendservice.data.entity.Department;
import com.aneeque.backendservice.data.repository.DepartmentRepository;
import com.aneeque.backendservice.dto.request.DepartmentDto;
import com.aneeque.backendservice.dto.response.DepartmentResponseDto;
import com.aneeque.backendservice.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void testSave() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        when(this.departmentRepository.save((Department) any())).thenReturn(department);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Name");
        DepartmentDto actualSaveResult = this.departmentServiceImpl.save(departmentDto);
        assertSame(departmentDto, actualSaveResult);
        assertEquals("Name", actualSaveResult.getName());
        verify(this.departmentRepository).save((Department) any());
        assertTrue(this.departmentServiceImpl.getAllDepartmentList().isEmpty());
    }

    @Test
    void testSaveShouldFailForEmptyName() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        when(this.departmentRepository.save((Department) any())).thenReturn(department);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.departmentServiceImpl.save(departmentDto));
        String expectedMessage = "invalid department name";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        Optional<Department> ofResult = Optional.<Department>of(department);

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");
        when(this.departmentRepository.save((Department) any())).thenReturn(department1);
        when(this.departmentRepository.findById((Long) any())).thenReturn(ofResult);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Name");
        DepartmentResponseDto actualUpdateResult = this.departmentServiceImpl.update(123L, departmentDto);
        assertEquals("01:01", actualUpdateResult.getCreatedAt().toLocalTime().toString());
        assertEquals("Name", actualUpdateResult.getName());
        assertEquals("0001-01-01", actualUpdateResult.getModifiedAt().toLocalDate().toString());
        verify(this.departmentRepository).findById((Long) any());
        verify(this.departmentRepository).save((Department) any());
        assertTrue(this.departmentServiceImpl.getAllDepartmentList().isEmpty());
    }

    @Test
    void testUpdateShouldFailIfNoDepartmentFound() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        when(this.departmentRepository.save((Department) any())).thenReturn(department);
        when(this.departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>empty());

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Name");
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> this.departmentServiceImpl.update(123L, departmentDto));
        String expectedMessage = "no department found";
        String actualMessage = noSuchElementException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.departmentRepository).findById((Long) any());
    }



    @Test
    void testGetAllDepartmentList() {
        when(this.departmentRepository.findAll()).thenReturn(new ArrayList<Department>());
        assertTrue(this.departmentServiceImpl.getAllDepartmentList().isEmpty());
        verify(this.departmentRepository).findAll();
    }

    @Test
    void testGetAllDepartmentList2() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        ArrayList<Department> departmentList = new ArrayList<Department>();
        departmentList.add(department);
        when(this.departmentRepository.findAll()).thenReturn(departmentList);
        List<DepartmentDto> actualAllDepartmentList = this.departmentServiceImpl.getAllDepartmentList();
        assertEquals(1, actualAllDepartmentList.size());
        assertEquals("Name", actualAllDepartmentList.get(0).getName());
        verify(this.departmentRepository).findAll();
    }

    @Test
    void testGetAllDepartmentList3() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        ArrayList<Department> departmentList = new ArrayList<Department>();
        departmentList.add(department1);
        departmentList.add(department);
        when(this.departmentRepository.findAll()).thenReturn(departmentList);
        List<DepartmentDto> actualAllDepartmentList = this.departmentServiceImpl.getAllDepartmentList();
        assertEquals(2, actualAllDepartmentList.size());
        assertEquals("DepartmentDto(name=Name)", actualAllDepartmentList.get(0).toString());
        assertEquals("DepartmentDto(name=Name)", actualAllDepartmentList.get(1).toString());
        verify(this.departmentRepository).findAll();
    }
}

