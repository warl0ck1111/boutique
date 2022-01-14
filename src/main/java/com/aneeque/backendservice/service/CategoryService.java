package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.data.repository.CategoryRepository;
import com.aneeque.backendservice.dto.request.CategoryRequestDto;
import com.aneeque.backendservice.dto.response.AttributeResponseDto;
import com.aneeque.backendservice.dto.response.CategoryResponseDto;
import com.aneeque.backendservice.enums.CategoryType;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttributeServiceImpl attributeService;

    @Autowired
    private PropertyService propertyService;



    @Transactional
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto, CategoryType categoryType) {
        Category category = new Category();

        List<Attribute> attributes = getAttributeService().getAttributeRepository().findAllById(categoryRequestDto.getAttributesIds());
        List<Category> subCategories = getCategoryRepository().findAllById(categoryRequestDto.getSubCategoryIds());

        BeanUtils.copyProperties(categoryRequestDto, category);

        category.setSubCategories(subCategories);
        category.setAttributes(attributes);
        category.setCategoryType(categoryType);
        Category savedCategory = categoryRepository.save(category);
        CategoryResponseDto responseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(savedCategory, responseDto);
        return responseDto;
    }

    public CategoryResponseDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid category id");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no Category found"));
        CategoryResponseDto responseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(category, responseDto);

        return responseDto;

    }

    @Transactional
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no Category found"));
        List<Attribute> attributes = getAttributeService().getAttributeRepository().findAllById(categoryRequestDto.getAttributesIds());
        List<Category> subCategories = getCategoryRepository().findAllById(categoryRequestDto.getSubCategoryIds());
        BeanUtils.copyProperties(categoryRequestDto, category);

        category.setSubCategories(subCategories);
        category.setAttributes(attributes);
        Category updatedCategory = categoryRepository.save(category);
        CategoryResponseDto responseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(updatedCategory, responseDto);
        return responseDto;
    }

    @Transactional
    public CategoryResponseDto addChildToParent(Long parentId, CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto savedChild = save(categoryRequestDto, CategoryType.SUB_CATEGORY);
        Category child = new Category();
        BeanUtils.copyProperties(savedChild, child);
        Category parent = categoryRepository.findById(parentId).get();
        parent.getSubCategories().add(child);
        Category updatedParent = categoryRepository.save(parent);
        CategoryResponseDto responseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(updatedParent, responseDto);
        return responseDto;
    }

    public Set<CategoryResponseDto> getAllParent() {
        Set<CategoryResponseDto> categoryResponseDtos = new HashSet<>();
        categoryRepository.findAllByCategoryType(CategoryType.DEPARTMENT).forEach(category -> {
            CategoryResponseDto responseDto = new CategoryResponseDto();
            BeanUtils.copyProperties(category, responseDto);
            categoryResponseDtos.add(responseDto);
        });
        return categoryResponseDtos;
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryResponseDto> getAll() {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> {
            CategoryResponseDto responseDto = new CategoryResponseDto();
            BeanUtils.copyProperties(category, responseDto);
            categoryResponseDtos.add(responseDto);
        });
        return categoryResponseDtos;
    }

    @Transactional
    public CategoryResponseDto assignAttributesToCategory(Long categoryId, Set<Long> attributeIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);

        category.setAttributes(attributes);
        Category updatedCategory = categoryRepository.save(category);

        CategoryResponseDto responseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(updatedCategory, responseDto);
        return responseDto;

    }


    public List<AttributeResponseDto> getAllAssignedAttributesToCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<AttributeResponseDto> attributeResponseDtos = new ArrayList<>();
        category.getAttributes().forEach(attribute -> {
            AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
            BeanUtils.copyProperties(attribute, attributeResponseDto);
            attributeResponseDtos.add(attributeResponseDto);
        });
        return attributeResponseDtos;
    }



}
