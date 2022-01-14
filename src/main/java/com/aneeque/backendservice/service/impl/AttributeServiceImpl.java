package com.aneeque.backendservice.service.impl;

import com.aneeque.backendservice.data.entity.Attribute;
import com.aneeque.backendservice.data.repository.AttributeRepository;
import com.aneeque.backendservice.dto.request.AttributeRequestDto;
import com.aneeque.backendservice.data.entity.Property;
import com.aneeque.backendservice.dto.request.PropertyRequestDto;
import com.aneeque.backendservice.dto.response.AttributeResponseDto;
import com.aneeque.backendservice.service.PropertyService;
import com.aneeque.backendservice.service.AttributeService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public AttributeResponseDto save(AttributeRequestDto attributeRequestDto) {

        Attribute attribute = new Attribute();
        List<Property> properties = propertyService.getPropertyRepository().findAllById(attributeRequestDto.getPropertyIds());
        BeanUtils.copyProperties(attributeRequestDto, attribute);
        attribute.setProperties(properties);
        Attribute savedAttribute = attributeRepository.save(new Attribute(attributeRequestDto.getName()));

        AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
        BeanUtils.copyProperties(savedAttribute, attributeResponseDto);

        return attributeResponseDto;
    }

    @Override
    public AttributeResponseDto update(Long id, AttributeRequestDto attributeRequestDto) {

        List<Property> properties = propertyService.getPropertyRepository().findAllById(attributeRequestDto.getPropertyIds());
        Attribute attribute = attributeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no attribute found"));

        BeanUtils.copyProperties(attributeRequestDto, attribute);
        attribute.setProperties(properties);


        Attribute updatedAttribute = attributeRepository.save(attribute);
        AttributeResponseDto attributeResponseDto = new AttributeResponseDto();

        BeanUtils.copyProperties(updatedAttribute, attributeResponseDto);
        return attributeResponseDto;
    }

    @Override
    public List<AttributeResponseDto> getAllAttributes() {
        List<AttributeResponseDto> attributeResponseDtos = new ArrayList<>();
        List<Attribute> attributes = attributeRepository.findAll();
        attributes.forEach(attribute -> {
            AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
            BeanUtils.copyProperties(attribute, attributeResponseDto);

            attributeResponseDtos.add(attributeResponseDto);
        });
        return attributeResponseDtos;
    }



    public AttributeResponseDto assignPropertiesToAttribute(Long attributeId, List<Long> propertyIds) {

        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(() -> new NoSuchElementException("no attribute found"));

        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyIds);
        attribute.setProperties(properties);
        Attribute updatedAttribute = attributeRepository.save(attribute);

        AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
        BeanUtils.copyProperties(updatedAttribute, attributeResponseDto);

        return attributeResponseDto;
    }

    public AttributeResponseDto findAttributeById(Long attributeId){
        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(() -> new NoSuchElementException("no attribute found"));
        AttributeResponseDto attributeResponseDto = new AttributeResponseDto();
        BeanUtils.copyProperties(attribute, attributeResponseDto);
        return attributeResponseDto;
    }



        public List<PropertyRequestDto> getAssignedPropertiesToAttribute(Long attributeId){
        List<PropertyRequestDto> propertyDtoList = new ArrayList<>();

        Attribute attribute = attributeRepository.findById(attributeId).orElseThrow(() -> new NoSuchElementException("no attribute found"));
        attribute.getProperties().forEach(property -> {
            PropertyRequestDto propertyDto = new PropertyRequestDto();
            BeanUtils.copyProperties(property, propertyDto);
            propertyDtoList.add(propertyDto);
        });
        return propertyDtoList;
    }
}
