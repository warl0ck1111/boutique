package com.aneeque.backendservice.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.data.repository.CategoryRepository;
import com.aneeque.backendservice.dto.request.CategoryRequestDto;
import com.aneeque.backendservice.enums.CategoryType;
import com.aneeque.backendservice.service.CategoryService;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.service.PropertyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
    void testGetAll() {
        when(this.categoryRepository.findAll()).thenReturn(new ArrayList<Category>());
        assertTrue(this.categoryService.getAll().isEmpty());
        verify(this.categoryRepository).findAll();
        assertTrue(this.categoryService.getAllParent().isEmpty());
    }

    @Test
    void testGetAll2() {
        Category category = new Category();
        category.setAttributes(new ArrayList<Attribute>());
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setSubCategories(new ArrayList<Category>());
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setId(123L);
        category.setName("Name");
        category.setCategoryType(CategoryType.DEPARTMENT);
        category.setDescription("The characteristics of someone or something");

        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList.add(category);
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(1, this.categoryService.getAll().size());
        verify(this.categoryRepository).findAll();
        assertTrue(this.categoryService.getAllParent().isEmpty());
    }

    @Test
    void testGetAll3() {
        Category category = new Category();
        category.setAttributes(new ArrayList<Attribute>());
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setSubCategories(new ArrayList<Category>());
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setId(123L);
        category.setName("Name");
        category.setCategoryType(CategoryType.DEPARTMENT);
        category.setDescription("The characteristics of someone or something");

        Category category1 = new Category();
        category1.setAttributes(new ArrayList<Attribute>());
        category1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category1.setSubCategories(new ArrayList<Category>());
        category1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category1.setId(123L);
        category1.setName("Name");
        category1.setCategoryType(CategoryType.DEPARTMENT);
        category1.setDescription("The characteristics of someone or something");

        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList.add(category1);
        categoryList.add(category);
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(2, this.categoryService.getAll().size());
        verify(this.categoryRepository).findAll();
        assertTrue(this.categoryService.getAllParent().isEmpty());
    }


}

