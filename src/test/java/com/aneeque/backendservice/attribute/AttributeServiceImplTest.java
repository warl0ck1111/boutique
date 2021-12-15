package com.aneeque.backendservice.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.department.Department;
import com.aneeque.backendservice.department.DepartmentRepository;
import com.aneeque.backendservice.department.DepartmentServiceImpl;
import com.aneeque.backendservice.property.Property;
import com.aneeque.backendservice.property.PropertyDto;
import com.aneeque.backendservice.property.PropertyRepository;
import com.aneeque.backendservice.property.PropertyService;

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

    @MockBean
    private PropertyService propertyService;

    @Test
    void testSave() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ArrayList<Property> propertyList = new ArrayList<Property>();
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        when(this.attributeRepository.save((Attribute) any())).thenReturn(attribute);

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        AttributeDto actualSaveResult = this.attributeServiceImpl.save(attributeDto);
        assertSame(attributeDto, actualSaveResult);
        assertEquals("Name", actualSaveResult.getName());
        verify(this.attributeRepository).save((Attribute) any());
        assertEquals(propertyList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testSave2() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        when(this.attributeRepository.save((Attribute) any())).thenReturn(attribute);

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("");
        assertThrows(IllegalArgumentException.class, () -> this.attributeServiceImpl.save(attributeDto));
    }

    @Test
    void testUpdate() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ArrayList<Property> propertyList = new ArrayList<Property>();
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        Optional<Attribute> ofResult = Optional.<Attribute>of(attribute);

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        Attribute attribute1 = new Attribute();
        attribute1.setDepartment(department1);
        attribute1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setProperties(new ArrayList<Property>());
        attribute1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setId(123L);
        attribute1.setName("Name");
        when(this.attributeRepository.save((Attribute) any())).thenReturn(attribute1);
        when(this.attributeRepository.findById((Long) any())).thenReturn(ofResult);

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        AttributeDto actualUpdateResult = this.attributeServiceImpl.update(123L, attributeDto);
        assertSame(attributeDto, actualUpdateResult);
        assertEquals("Name", actualUpdateResult.getName());
        verify(this.attributeRepository).findById((Long) any());
        verify(this.attributeRepository).save((Attribute) any());
        assertEquals(propertyList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testUpdate2() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        when(this.attributeRepository.save((Attribute) any())).thenReturn(attribute);
        when(this.attributeRepository.findById((Long) any())).thenReturn(Optional.<Attribute>empty());

        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setDepartment("Department");
        attributeDto.setName("Name");
        assertThrows(NoSuchElementException.class, () -> this.attributeServiceImpl.update(123L, attributeDto));
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
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(attribute);
        when(this.attributeRepository.findAll()).thenReturn(attributeList);
        List<AttributeDto> actualAllAttributes = this.attributeServiceImpl.getAllAttributes();
        assertEquals(1, actualAllAttributes.size());
        assertEquals("Name", actualAllAttributes.get(0).getName());
        verify(this.attributeRepository).findAll();
    }

    @Test
    void testGetAllAttributes3() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        Attribute attribute1 = new Attribute();
        attribute1.setDepartment(department1);
        attribute1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setProperties(new ArrayList<Property>());
        attribute1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setId(123L);
        attribute1.setName("Name");

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(attribute1);
        attributeList.add(attribute);
        when(this.attributeRepository.findAll()).thenReturn(attributeList);
        List<AttributeDto> actualAllAttributes = this.attributeServiceImpl.getAllAttributes();
        assertEquals(2, actualAllAttributes.size());
        assertEquals("Name", actualAllAttributes.get(1).getName());
        assertEquals("Name", actualAllAttributes.get(0).getName());
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

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department1);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ArrayList<Property> propertyList = new ArrayList<Property>();
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(attribute);
        when(this.attributeRepository.findAllByDepartment((Department) any())).thenReturn(attributeList);
        List<AttributeDto> actualAllAttributesByDepartmentId = this.attributeServiceImpl
                .getAllAttributesByDepartmentId(123L);
        assertEquals(1, actualAllAttributesByDepartmentId.size());
        assertEquals("Name", actualAllAttributesByDepartmentId.get(0).getName());
        verify(this.departmentServiceImpl).getDepartmentRepository();
        verify(departmentRepository).findById((Long) any());
        verify(this.attributeRepository).findAllByDepartment((Department) any());
        assertEquals(propertyList, this.attributeServiceImpl.getAllAttributes());
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

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department1);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ArrayList<Property> propertyList = new ArrayList<Property>();
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");

        Department department2 = new Department();
        department2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department2.setId(123L);
        department2.setName("Name");

        Attribute attribute1 = new Attribute();
        attribute1.setDepartment(department2);
        attribute1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setProperties(new ArrayList<Property>());
        attribute1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setId(123L);
        attribute1.setName("Name");

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(attribute1);
        attributeList.add(attribute);
        when(this.attributeRepository.findAllByDepartment((Department) any())).thenReturn(attributeList);
        List<AttributeDto> actualAllAttributesByDepartmentId = this.attributeServiceImpl
                .getAllAttributesByDepartmentId(123L);
        assertEquals(2, actualAllAttributesByDepartmentId.size());
        assertEquals("Name", actualAllAttributesByDepartmentId.get(1).getName());
        assertEquals("Name", actualAllAttributesByDepartmentId.get(0).getName());
        verify(this.departmentServiceImpl).getDepartmentRepository();
        verify(departmentRepository).findById((Long) any());
        verify(this.attributeRepository).findAllByDepartment((Department) any());
        assertEquals(propertyList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testAssignPropertiesToAttribute() {
        PropertyRepository propertyRepository = mock(PropertyRepository.class);
        when(propertyRepository.findAllById((Iterable<Long>) any())).thenReturn(new ArrayList<Property>());
        when(this.propertyService.getPropertyRepository()).thenReturn(propertyRepository);

        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        Optional<Attribute> ofResult = Optional.<Attribute>of(attribute);

        Department department1 = new Department();
        department1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department1.setId(123L);
        department1.setName("Name");

        Attribute attribute1 = new Attribute();
        attribute1.setDepartment(department1);
        attribute1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setProperties(new ArrayList<Property>());
        attribute1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute1.setId(123L);
        attribute1.setName("Name");
        when(this.attributeRepository.save((Attribute) any())).thenReturn(attribute1);
        when(this.attributeRepository.findById((Long) any())).thenReturn(ofResult);
        ArrayList<Long> resultLongList = new ArrayList<Long>();
        assertEquals("Name", this.attributeServiceImpl.assignPropertiesToAttribute(123L, resultLongList).getName());
        verify(this.propertyService).getPropertyRepository();
        verify(propertyRepository).findAllById((Iterable<Long>) any());
        verify(this.attributeRepository).findById((Long) any());
        verify(this.attributeRepository).save((Attribute) any());
        assertEquals(resultLongList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testGetAssignedPropertiesToAttribute() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(new ArrayList<Property>());
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        Optional<Attribute> ofResult = Optional.<Attribute>of(attribute);
        when(this.attributeRepository.findById((Long) any())).thenReturn(ofResult);
        List<PropertyDto> actualAssignedPropertiesToAttribute = this.attributeServiceImpl
                .getAssignedPropertiesToAttribute(123L);
        assertTrue(actualAssignedPropertiesToAttribute.isEmpty());
        verify(this.attributeRepository).findById((Long) any());
        assertEquals(actualAssignedPropertiesToAttribute, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testGetAssignedPropertiesToAttribute2() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Property property = new Property();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        property.setAttributes(attributeList);
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");

        ArrayList<Property> propertyList = new ArrayList<Property>();
        propertyList.add(property);

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        Optional<Attribute> ofResult = Optional.<Attribute>of(attribute);
        when(this.attributeRepository.findById((Long) any())).thenReturn(ofResult);
        List<PropertyDto> actualAssignedPropertiesToAttribute = this.attributeServiceImpl
                .getAssignedPropertiesToAttribute(123L);
        assertEquals(1, actualAssignedPropertiesToAttribute.size());
        PropertyDto getResult = actualAssignedPropertiesToAttribute.get(0);
        assertEquals("Data", getResult.getData());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        verify(this.attributeRepository).findById((Long) any());
        assertEquals(attributeList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testGetAssignedPropertiesToAttribute3() {
        Department department = new Department();
        department.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        department.setId(123L);
        department.setName("Name");

        Property property = new Property();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        property.setAttributes(attributeList);
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");

        Property property1 = new Property();
        property1.setAttributes(new ArrayList<Attribute>());
        property1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property1.setId(123L);
        property1.setData("Data");
        property1.setDescription("The characteristics of someone or something");

        ArrayList<Property> propertyList = new ArrayList<Property>();
        propertyList.add(property1);
        propertyList.add(property);

        Attribute attribute = new Attribute();
        attribute.setDepartment(department);
        attribute.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setProperties(propertyList);
        attribute.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        attribute.setId(123L);
        attribute.setName("Name");
        Optional<Attribute> ofResult = Optional.<Attribute>of(attribute);
        when(this.attributeRepository.findById((Long) any())).thenReturn(ofResult);
        List<PropertyDto> actualAssignedPropertiesToAttribute = this.attributeServiceImpl
                .getAssignedPropertiesToAttribute(123L);
        assertEquals(2, actualAssignedPropertiesToAttribute.size());
        PropertyDto getResult = actualAssignedPropertiesToAttribute.get(1);
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Data", getResult.getData());
        PropertyDto getResult1 = actualAssignedPropertiesToAttribute.get(0);
        assertEquals("The characteristics of someone or something", getResult1.getDescription());
        assertEquals("Data", getResult1.getData());
        verify(this.attributeRepository).findById((Long) any());
        assertEquals(attributeList, this.attributeServiceImpl.getAllAttributes());
    }

    @Test
    void testGetAssignedPropertiesToAttribute4() {
        when(this.attributeRepository.findById((Long) any())).thenReturn(Optional.<Attribute>empty());
        assertThrows(NoSuchElementException.class, () -> this.attributeServiceImpl.getAssignedPropertiesToAttribute(123L));
        verify(this.attributeRepository).findById((Long) any());
    }

}




