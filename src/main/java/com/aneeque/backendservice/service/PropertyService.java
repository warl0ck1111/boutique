package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Property;
import com.aneeque.backendservice.data.repository.PropertyRepository;
import com.aneeque.backendservice.dto.request.PropertyRequestDto;
import com.aneeque.backendservice.dto.response.PropertyResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.aneeque.backendservice.util.Util.hasValue;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;


    @Transactional
    public PropertyResponseDto save(PropertyRequestDto req) {

        Property property = new Property();
        BeanUtils.copyProperties(req,property);
        Property savedProperty = propertyRepository.save(property);

        PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
        BeanUtils.copyProperties(savedProperty, propertyResponseDto);
        return propertyResponseDto;
    }

    
    public PropertyResponseDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid property id");
        Property property = propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no property found"));

        PropertyRequestDto propertyDto = new PropertyRequestDto();
        PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
        BeanUtils.copyProperties(property,propertyResponseDto);

        return propertyResponseDto;
    }

    @Transactional
    
    public PropertyResponseDto update(Long id, PropertyRequestDto propertyDto) {

        Property savedProperty = propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no property found"));
        BeanUtils.copyProperties(propertyDto, savedProperty);

        Property updatedProperty = propertyRepository.save(savedProperty);

        PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
        BeanUtils.copyProperties(updatedProperty, propertyResponseDto);

        return propertyResponseDto;
    }

    @Transactional
    
    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }

    
    public List<PropertyResponseDto> getAll() {
        List<PropertyResponseDto> propertyDtoList = new ArrayList<>();
        List<Property> properties = propertyRepository.findAll();

        properties.forEach(property -> {
            PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
            BeanUtils.copyProperties(property, propertyResponseDto);
            propertyDtoList.add(propertyResponseDto);
        });
        return propertyDtoList;
    }


}
