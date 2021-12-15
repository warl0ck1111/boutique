package com.aneeque.backendservice.attribute;

import com.aneeque.backendservice.department.Department;
import com.aneeque.backendservice.department.DepartmentServiceImpl;
import com.aneeque.backendservice.property.Property;
import com.aneeque.backendservice.property.PropertyDto;
import com.aneeque.backendservice.property.PropertyService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.aneeque.backendservice.util.Util.hasValue;

/**
 * @author Okala III
 */

@Service
@Getter
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private PropertyService propertyService;

    @Override
    public AttributeDto save(AttributeDto attributeDto) {

        if (!hasValue(attributeDto.getName()))
            throw new IllegalArgumentException("attribute name field cannot be empty");

        Attribute savedAttribute = attributeRepository.save(new Attribute(attributeDto.getName()));

        BeanUtils.copyProperties(savedAttribute, attributeDto);

        return attributeDto;
    }

    @Override
    public AttributeDto update(Long id, AttributeDto attributeDto) {
        if (!hasValue(attributeDto.getName()))
            throw new IllegalArgumentException("attribute name field cannot be empty");
        if (!hasValue(attributeDto.getDepartment()))
            throw new IllegalArgumentException("attribute department field cannot be empty");

        Attribute savedAttribute = attributeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no attribute found"));

        BeanUtils.copyProperties(attributeDto, savedAttribute);
        Attribute updatedAttribute = attributeRepository.save(savedAttribute);

        BeanUtils.copyProperties(updatedAttribute, attributeDto);


        return attributeDto;
    }

    @Override
    public List<AttributeDto> getAllAttributes() {
        List<AttributeDto> attributeDtoList = new ArrayList<>();
        List<Attribute> attributes = attributeRepository.findAll();
        attributes.forEach(attribute -> {
            AttributeDto attributeDto = new AttributeDto();
            BeanUtils.copyProperties(attribute, attributeDto);

            attributeDtoList.add(attributeDto);
        });
        return attributeDtoList;
    }

    @Override
    public List<AttributeDto> getAllAttributesByDepartmentId(Long id) {
        List<AttributeDto> attributeDtoList = new ArrayList<>();

        Department department = departmentService.getDepartmentRepository().findById(id).orElseThrow(() -> new IllegalArgumentException("invalid department Id"));
        List<Attribute> attributes = attributeRepository.findAllByDepartment(department);
        attributes.forEach(attribute -> {
            AttributeDto attributeDto = new AttributeDto();
            BeanUtils.copyProperties(attribute, attributeDto);

            attributeDtoList.add(attributeDto);
        });
        return attributeDtoList;
    }

    public AttributeDto assignPropertiesToAttribute(Long attributeId, List<Long> propertyIds) {

        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(() -> new NoSuchElementException("no attribute found"));

        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyIds);
        attribute.setProperties(properties);
        Attribute updatedAttribute = attributeRepository.save(attribute);

        AttributeDto attributeDto = new AttributeDto();
        BeanUtils.copyProperties(updatedAttribute, attributeDto);

        return attributeDto;
    }

    public List<PropertyDto> getAssignedPropertiesToAttribute(Long attributeId){
        List<PropertyDto> propertyDtoList = new ArrayList<>();

        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(() -> new NoSuchElementException("no attribute found"));
        attribute.getProperties().forEach(property -> {
            PropertyDto propertyDto = new PropertyDto();
            BeanUtils.copyProperties(property, propertyDto);
            propertyDtoList.add(propertyDto);
        });
        return propertyDtoList;
    }
}
