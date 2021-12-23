package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.entity.Category;
import com.aneeque.backendservice.data.repository.CategoryRepository;
import com.aneeque.backendservice.dto.request.AttributeDto;
import com.aneeque.backendservice.dto.request.CategoryDto;
import com.aneeque.backendservice.enums.CategoryType;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.data.entity.Property;
import com.aneeque.backendservice.dto.request.PropertyDto;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.aneeque.backendservice.util.Util.hasValue;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class CategoryService implements CrudService<Category, CategoryDto> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttributeServiceImpl attributeService;

    @Autowired
    private PropertyService propertyService;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if (!hasValue(categoryDto.getName())) throw new IllegalArgumentException("category name can not be empty");
        Category category = new Category();
        Set<Category> categoryList = (Set<Category>)getCategoryRepository().findAllById(categoryDto.getLevelIds());
        BeanUtils.copyProperties(categoryDto, category);
        category.setSubCategories(categoryList);
        category.setCategoryType(CategoryType.SUB_CATEGORY);
        Category savedCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(savedCategory, categoryDto);
        return categoryDto;
    }
    public CategoryDto save(CategoryDto categoryDto, CategoryType categoryType) {
        if (!hasValue(categoryDto.getName())) throw new IllegalArgumentException("category name can not be empty");
        Category category = new Category();
        Set<Category> categoryList = (Set<Category>)getCategoryRepository().findAllById(categoryDto.getLevelIds());
        BeanUtils.copyProperties(categoryDto, category);
        category.setSubCategories(categoryList);
        category.setCategoryType(categoryType);
        Category savedCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(savedCategory, categoryDto);
        return categoryDto;
    }

    @Override
    public CategoryDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid category id");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no Category found"));
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);

        return categoryDto;

    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (!hasValue(categoryDto.getName())) throw new IllegalArgumentException("category name can not be empty");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no Category found"));
        Set<Category> categoryList = (Set<Category>) getCategoryRepository().findAllById(categoryDto.getLevelIds());
        BeanUtils.copyProperties(categoryDto, category);

        category.setSubCategories(categoryList);
        Category updatedCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(updatedCategory, categoryDto);
        return categoryDto;
    }

    public CategoryDto addChild(Long parentId, CategoryDto categoryDto) {
        CategoryDto savedChild = save(categoryDto);
        Category child = new Category();
        BeanUtils.copyProperties(savedChild, child);
        Category parent = categoryRepository.findById(parentId).get();
        parent.getSubCategories().add(child);
        Category updatedParent = categoryRepository.save(parent);
        BeanUtils.copyProperties(updatedParent, categoryDto);
        return categoryDto;
    }

    public Set<CategoryDto> getAllParent() {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        categoryRepository.findAllByCategoryType(CategoryType.DEPARTMENT).forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtoList.add(categoryDto);
        });
        return categoryDtoList;
    }

    public CategoryDto assignAttributesToCategory(Long categoryId, List<Long> attributeIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);

        category.setAttributes(attributes);
        Category updatedCategory = categoryRepository.save(category);

        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(updatedCategory, categoryDto);
        return categoryDto;

    }

    public CategoryDto assignPropertiesToCategory(Long categoryId, List<Long> propertyIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyIds);

        category.setProperties(properties);
        Category updatedCategory = categoryRepository.save(category);

        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(updatedCategory, categoryDto);
        return categoryDto;

    }


    public CategoryDto assignAttributesAndPropertiesToCategory(Long categoryId, List<Long> attributeIds, List<Long> propertyIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyIds);

        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);

        category.setAttributes(attributes);
        category.setProperties(properties);
        Category updatedCategory = categoryRepository.save(category);

        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(updatedCategory, categoryDto);
        return categoryDto;

    }


    public List<AttributeDto> getAllAssignedAttributesToCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<AttributeDto> attributeDtoList = new ArrayList<>();
        category.getAttributes().forEach(attribute -> {
            AttributeDto attributeDto = new AttributeDto();
            BeanUtils.copyProperties(attribute, attributeDto);
            attributeDtoList.add(attributeDto);
        });
        return attributeDtoList;
    }


    public List<PropertyDto> getAllAssignedPropertiesToCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<PropertyDto> propertyDtoList = new ArrayList<>();
        category.getProperties().forEach(property -> {
            PropertyDto propertyDto = new PropertyDto();
            BeanUtils.copyProperties(property, propertyDto);
            propertyDtoList.add(propertyDto);
        });
        return propertyDtoList;
    }

    public CategoryDto getAllAssignedAttributesAndPropertiesToCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("no category found"));

        List<AttributeDto> attributeDtoList = new ArrayList<>();
        List<PropertyDto> propertyDtoList = new ArrayList<>();

        category.getAttributes().forEach(attribute -> {
            AttributeDto attributeDto = new AttributeDto();
            BeanUtils.copyProperties(attribute, attributeDto);
            attributeDtoList.add(attributeDto);
        });

        category.getProperties().forEach(property -> {
            PropertyDto propertyDto = new PropertyDto();
            BeanUtils.copyProperties(property, propertyDto);
            propertyDtoList.add(propertyDto);
        });

        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);

        categoryDto.setAttributes(attributeDtoList);
        categoryDto.setProperties(propertyDtoList);
        return categoryDto;
    }


}
