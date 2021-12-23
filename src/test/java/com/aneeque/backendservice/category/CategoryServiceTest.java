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
import java.util.List;

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


}

