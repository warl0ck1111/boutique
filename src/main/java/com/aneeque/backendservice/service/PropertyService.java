package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Property;
import com.aneeque.backendservice.data.repository.PropertyRepository;
import com.aneeque.backendservice.dto.request.PropertyDto;
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
public class PropertyService implements CrudService<Property, PropertyDto> {

    @Autowired
    private PropertyRepository propertyRepository;


    @Override
    public PropertyDto save(PropertyDto propertyDto) {
        if (!hasValue(propertyDto.getData())) throw new IllegalArgumentException("data field cannot be empty");
        if (!hasValue(propertyDto.getDescription()))
            throw new IllegalArgumentException("description field cannot be empty");

        Property property = new Property(propertyDto.getDescription(), propertyDto.getData());
        Property savedProperty = propertyRepository.save(property);

        BeanUtils.copyProperties(savedProperty, propertyDto);
        return propertyDto;
    }

    @Override
    public PropertyDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid property id");
        Property property = propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no property found"));
        
        PropertyDto propertyDto = new PropertyDto();
        BeanUtils.copyProperties(property,propertyDto);

        return propertyDto;
    }

    @Override
    public PropertyDto update(Long id, PropertyDto propertyDto) {
        if (!hasValue(propertyDto.getData())) throw new IllegalArgumentException("data field cannot be empty");
        if (!hasValue(propertyDto.getDescription()))
            throw new IllegalArgumentException("description field cannot be empty");

        Property savedProperty = propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no property found"));
        BeanUtils.copyProperties(propertyDto, savedProperty);

        Property updatedProperty = propertyRepository.save(savedProperty);
        BeanUtils.copyProperties(updatedProperty, propertyDto);

        return propertyDto;
    }

    @Override
    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<PropertyDto> getAll() {
        List<PropertyDto> propertyDtoList = new ArrayList<>();
        List<Property> properties = propertyRepository.findAll();

        properties.forEach(property -> {
            PropertyDto propertyDto = new PropertyDto();
            BeanUtils.copyProperties(property, propertyDto);
            propertyDtoList.add(propertyDto);
        });
        return propertyDtoList;
    }


}
