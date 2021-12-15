package com.aneeque.backendservice.category;

import com.aneeque.backendservice.attribute.Attribute;
import com.aneeque.backendservice.attribute.AttributeDto;
import com.aneeque.backendservice.attribute.AttributeServiceImpl;
import com.aneeque.backendservice.property.Property;
import com.aneeque.backendservice.property.PropertyDto;
import com.aneeque.backendservice.property.PropertyService;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        Category category = new Category(categoryDto.getName());
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
        BeanUtils.copyProperties(categoryDto, category);
        Category updatedCategory = categoryRepository.save(category);


        BeanUtils.copyProperties(updatedCategory, categoryDto);
        return categoryDto;
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
