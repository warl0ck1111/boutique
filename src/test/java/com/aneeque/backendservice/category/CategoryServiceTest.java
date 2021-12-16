package com.aneeque.backendservice.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.data.repository.CategoryRepository;
import com.aneeque.backendservice.dto.request.AttributeDto;
import com.aneeque.backendservice.dto.request.CategoryDto;
import com.aneeque.backendservice.service.CategoryService;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.data.entity.Property;
import com.aneeque.backendservice.dto.request.PropertyDto;
import com.aneeque.backendservice.service.PropertyService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryService.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @MockBean
    private AttributeServiceImpl attributeServiceImpl;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private PropertyService propertyService;

    @Test
    void testSave() {
        assertThrows(IllegalArgumentException.class, () -> this.categoryService.save(new CategoryDto()));
    }

    @Test
    void testSave2() {
        Category category = new Category();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        category.setAttributes(attributeList);
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setId(123L);
        category.setName("Name");
        category.setProperties(new ArrayList<Property>());
        category.setParentId(123L);
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        ArrayList<PropertyDto> properties = new ArrayList<PropertyDto>();
        CategoryDto categoryDto = new CategoryDto("Name", properties, new ArrayList<AttributeDto>(), 123L, 1L, 1L);

        CategoryDto actualSaveResult = this.categoryService.save(categoryDto);
        assertSame(categoryDto, actualSaveResult);
        assertEquals(123L, actualSaveResult.getParentId().longValue());
        assertEquals("Name", actualSaveResult.getName());
        verify(this.categoryRepository).save((Category) any());
        assertEquals(attributeList, this.categoryService.getAll());
    }

    @Test
    void testSave3() {
        Category category = new Category();
        category.setAttributes(new ArrayList<Attribute>());
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setId(123L);
        category.setName("Name");
        category.setProperties(new ArrayList<Property>());
        category.setParentId(123L);
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        ArrayList<PropertyDto> properties = new ArrayList<PropertyDto>();
        assertThrows(IllegalArgumentException.class,
                () -> this.categoryService.save(new CategoryDto("", properties, new ArrayList<AttributeDto>(), 123L, 1L, 1L)));
    }

    @Test
    void testUpdate() {
        Category category = new Category();
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        category.setAttributes(attributeList);
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setId(123L);
        category.setName("Name");
        category.setProperties(new ArrayList<Property>());
        category.setParentId(123L);
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        CategoryDto categoryDto = mock(CategoryDto.class);
        doNothing().when(categoryDto).setParentId((Long) any());
        doNothing().when(categoryDto).setName((String) any());
        when(categoryDto.getName()).thenReturn("Name");
        this.categoryService.save(categoryDto);
        verify(this.categoryRepository).save((Category) any());
        verify(categoryDto, atLeast(1)).getName();
        verify(categoryDto).setName((String) any());
        verify(categoryDto).setParentId((Long) any());
        assertEquals(attributeList, this.categoryService.getAll());
    }
}

