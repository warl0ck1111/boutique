package com.aneeque.backendservice.category.department.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.category.department.Department;
import com.aneeque.backendservice.category.department.DepartmentRepository;
import com.aneeque.backendservice.category.department.DepartmentServiceImpl;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AttributeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AttributeServiceImplTest {
    @MockBean
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeServiceImpl attributeServiceImpl;

    @MockBean
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void testSave() {
        when(this.attributeRepository.save((Attribute) any())).thenReturn(new Attribute());

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        assertSame(attributeDto, this.attributeServiceImpl.save(attributeDto));
        verify(this.attributeRepository).save((Attribute) any());
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
    }

    @Test
    void testSaveShouldFailWhenAttributeNameIsEmpty() {
        when(this.attributeRepository.save((Attribute) any())).thenReturn(new Attribute());

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.attributeServiceImpl.save(attributeDto));
        String expectedMessage = "attribute name field cannot be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        when(this.attributeRepository.save((Attribute) any())).thenReturn(new Attribute());
        when(this.attributeRepository.findById((Long) any())).thenReturn(Optional.<Attribute>of(new Attribute()));

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        assertSame(attributeDto, this.attributeServiceImpl.update(123L, attributeDto));
        verify(this.attributeRepository).findById((Long) any());
        verify(this.attributeRepository).save((Attribute) any());
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
    }

    @Test
    void testUpdateShouldFailWhenNoAttributeFound() {
        when(this.attributeRepository.save((Attribute) any())).thenReturn(new Attribute());
        when(this.attributeRepository.findById((Long) any())).thenReturn(Optional.<Attribute>empty());

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> this.attributeServiceImpl.update(123L, attributeDto));
        String expectedMessage = "no attribute found";
        String actualMessage = noSuchElementException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.attributeRepository).findById((Long) any());
    }



    @Test
    void testGetAllAttributes() {
        when(this.attributeRepository.findAll()).thenReturn(new ArrayList<Attribute>());
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
        verify(this.attributeRepository).findAll();
    }

    @Test
    void testGetAllAttributes2() {
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute());
        when(this.attributeRepository.findAll()).thenReturn(attributeList);
        assertEquals(1, this.attributeServiceImpl.getAllAttributes().size());
        verify(this.attributeRepository).findAll();
    }

    @Test
    void testGetAllAttributes3() {
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute());
        attributeList.add(new Attribute());
        when(this.attributeRepository.findAll()).thenReturn(attributeList);
        assertEquals(2, this.attributeServiceImpl.getAllAttributes().size());
        verify(this.attributeRepository).findAll();
    }

    @Test
    void testGetAllAttributesByDepartmentId() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        DepartmentRepository departmentRepository = mock(DepartmentRepository.class);
        when(departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>of(department));
        when(this.departmentServiceImpl.getDepartmentRepository()).thenReturn(departmentRepository);
        when(this.attributeRepository.findAllByDepartment((Department) any())).thenReturn(new ArrayList<Attribute>());
        List<AttributeDto> actualAllAttributesByDepartmentId = this.attributeServiceImpl
                .getAllAttributesByDepartmentId(123L);
        assertTrue(actualAllAttributesByDepartmentId.isEmpty());
        verify(this.departmentServiceImpl).getDepartmentRepository();
        verify(departmentRepository).findById((Long) any());
        verify(this.attributeRepository).findAllByDepartment((Department) any());
        assertEquals(actualAllAttributesByDepartmentId, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testGetAllAttributesByDepartmentId2() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        DepartmentRepository departmentRepository = mock(DepartmentRepository.class);
        when(departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>of(department));
        when(this.departmentServiceImpl.getDepartmentRepository()).thenReturn(departmentRepository);

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute());
        when(this.attributeRepository.findAllByDepartment((Department) any())).thenReturn(attributeList);
        assertEquals(1, this.attributeServiceImpl.getAllAttributesByDepartmentId(123L).size());
        verify(this.departmentServiceImpl).getDepartmentRepository();
        verify(departmentRepository).findById((Long) any());
        verify(this.attributeRepository).findAllByDepartment((Department) any());
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
    }

    @Test
    void testGetAllAttributesByDepartmentId3() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");
        DepartmentRepository departmentRepository = mock(DepartmentRepository.class);
        when(departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>of(department));
        when(this.departmentServiceImpl.getDepartmentRepository()).thenReturn(departmentRepository);

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute());
        attributeList.add(new Attribute());
        when(this.attributeRepository.findAllByDepartment((Department) any())).thenReturn(attributeList);
        assertEquals(2, this.attributeServiceImpl.getAllAttributesByDepartmentId(123L).size());
        verify(this.departmentServiceImpl).getDepartmentRepository();
        verify(departmentRepository).findById((Long) any());
        verify(this.attributeRepository).findAllByDepartment((Department) any());
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
    }
}

