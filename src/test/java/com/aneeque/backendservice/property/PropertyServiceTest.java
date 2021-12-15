package com.aneeque.backendservice.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.attribute.Attribute;

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

@ContextConfiguration(classes = {PropertyService.class})
@ExtendWith(SpringExtension.class)
class PropertyServiceTest {

    @MockBean
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyService propertyService;

    @Test
    void testSave() {
        Property property = new Property();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        property.setAttributes(attributeList);
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");
        when(this.propertyRepository.save((Property) any())).thenReturn(property);
        PropertyDto propertyDto = new PropertyDto("The characteristics of someone or something", "Data");

        PropertyDto actualSaveResult = this.propertyService.save(propertyDto);
        assertSame(propertyDto, actualSaveResult);
        assertEquals("Data", actualSaveResult.getData());
        assertEquals("The characteristics of someone or something", actualSaveResult.getDescription());
        verify(this.propertyRepository).save((Property) any());
        assertEquals(attributeList, this.propertyService.getAll());
    }

    @Test
    void testSaveShouldFailWhenDescriptionFieldIsEmpty() {
        Property property = new Property();
        property.setAttributes(new ArrayList<Attribute>());
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");
        when(this.propertyRepository.save((Property) any())).thenReturn(property);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.propertyService.save(new PropertyDto("", "Data")));
        String expectedMessage = "description field cannot be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSaveShouldFailWhenDataFieldIsEmpty() {
        Property property = new Property();
        property.setAttributes(new ArrayList<Attribute>());
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");
        when(this.propertyRepository.save((Property) any())).thenReturn(property);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> this.propertyService.save(new PropertyDto("The characteristics of someone or something", "")));
        String expectedMessage = "data field cannot be empty";
        String actualMessage = illegalArgumentException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetById() {
        Property property = new Property();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        property.setAttributes(attributeList);
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");
        Optional<Property> ofResult = Optional.<Property>of(property);
        when(this.propertyRepository.findById((Long) any())).thenReturn(ofResult);
        PropertyDto actualById = this.propertyService.getById(123L);
        assertEquals("Data", actualById.getData());
        assertEquals("The characteristics of someone or something", actualById.getDescription());
        verify(this.propertyRepository).findById((Long) any());
        assertEquals(attributeList, this.propertyService.getAll());
    }

    @Test
    void testGetByIdShouldFailIfNoPropertyFound() {
        when(this.propertyRepository.findById((Long) any())).thenReturn(Optional.<Property>empty());
        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, () -> this.propertyService.getById(123L));
        String expectedMessage = "no property found";
        String actualMessage = noSuchElementException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(this.propertyRepository).findById((Long) any());
    }


    @Test
    void testUpdate() {
        Property property = new Property();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        property.setAttributes(attributeList);
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");
        Optional<Property> ofResult = Optional.<Property>of(property);

        Property property1 = new Property();
        property1.setAttributes(new ArrayList<Attribute>());
        property1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property1.setId(123L);
        property1.setData("Data");
        property1.setDescription("The characteristics of someone or something");
        when(this.propertyRepository.save((Property) any())).thenReturn(property1);
        when(this.propertyRepository.findById((Long) any())).thenReturn(ofResult);
        PropertyDto propertyDto = new PropertyDto("The characteristics of someone or something", "Data");

        PropertyDto actualUpdateResult = this.propertyService.update(123L, propertyDto);
        assertSame(propertyDto, actualUpdateResult);
        assertEquals("Data", actualUpdateResult.getData());
        assertEquals("The characteristics of someone or something", actualUpdateResult.getDescription());
        verify(this.propertyRepository).findById((Long) any());
        verify(this.propertyRepository).save((Property) any());
        assertEquals(attributeList, this.propertyService.getAll());
    }

    @Test
    void testDelete() {
        doNothing().when(this.propertyRepository).deleteById((Long) any());
        this.propertyService.delete(123L);
        verify(this.propertyRepository).deleteById((Long) any());
        assertTrue(this.propertyService.getAll().isEmpty());
    }

    @Test
    void testGetAll() {
        when(this.propertyRepository.findAll()).thenReturn(new ArrayList<Property>());
        assertTrue(this.propertyService.getAll().isEmpty());
        verify(this.propertyRepository).findAll();
    }

    @Test
    void testGetAll2() {
        Property property = new Property();
        property.setAttributes(new ArrayList<Attribute>());
        property.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        property.setId(123L);
        property.setData("Data");
        property.setDescription("The characteristics of someone or something");

        ArrayList<Property> propertyList = new ArrayList<Property>();
        propertyList.add(property);
        when(this.propertyRepository.findAll()).thenReturn(propertyList);
        List<PropertyDto> actualAll = this.propertyService.getAll();
        assertEquals(1, actualAll.size());
        PropertyDto getResult = actualAll.get(0);
        assertEquals("Data", getResult.getData());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        verify(this.propertyRepository).findAll();
    }

}

