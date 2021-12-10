package com.aneeque.backendservice.category.department.attribute;

import com.aneeque.backendservice.category.department.Department;
import com.aneeque.backendservice.category.department.DepartmentServiceImpl;
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
}
