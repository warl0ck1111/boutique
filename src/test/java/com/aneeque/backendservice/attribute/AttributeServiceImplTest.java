package com.aneeque.backendservice.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.repository.AttributeRepository;
import com.aneeque.backendservice.dto.response.AttributeResponseDto;
import com.aneeque.backendservice.service.PropertyService;
import com.aneeque.backendservice.service.impl.DepartmentServiceImpl;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.aneeque.backendservice.dto.request.AttributeRequestDto;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(classes = {AttributeServiceImpl.class})
//@ContextConfiguration(classes = {AttributeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AttributeServiceImplTest {
    @MockBean
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeServiceImpl attributeServiceImpl;

    @MockBean
    private DepartmentServiceImpl departmentServiceImpl;

    @MockBean
    PropertyService propertyService;




    @Test
    void testUpdate() {
        when(this.attributeRepository.save((Attribute) any())).thenReturn(new Attribute());
        when(this.attributeRepository.findById((Long) any())).thenReturn(Optional.<Attribute>of(new Attribute()));
        AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
        attributeResponseDto.setName("Name");
        assertTrue(this.attributeServiceImpl.getAllAttributes().isEmpty());
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




}

