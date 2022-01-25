package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.ShippingAddress;
import com.aneeque.backendservice.data.repository.ShippingAddressRepository;
import com.aneeque.backendservice.dto.request.ShippingAddressDto;
import com.aneeque.backendservice.exception.ApiRequestException;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class ShippingAddressService implements CrudService<ShippingAddress, ShippingAddressDto> {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;


    @Transactional
    @Override
    public ShippingAddressDto save(ShippingAddressDto shippingAddressDto) {
        ShippingAddress shippingAddress = new ShippingAddress();
        BeanUtils.copyProperties(shippingAddressDto, shippingAddress);
        ShippingAddress savedShippingAddress = shippingAddressRepository.save(shippingAddress);
        BeanUtils.copyProperties(savedShippingAddress, shippingAddressDto);
        return shippingAddressDto;
    }

    @Override
    public ShippingAddressDto getById(Long id) {

        ShippingAddress shippingAddress = shippingAddressRepository.getById(id);
        if(Objects.isNull(shippingAddress)){
            throw new ApiRequestException("Shipping address data does not exist", HttpStatus.BAD_REQUEST);
        }
        ShippingAddressDto response = new ShippingAddressDto();
        BeanUtils.copyProperties(shippingAddress, response);
        return response;
    }

    @Transactional
    @Override
    public ShippingAddressDto update(Long id, ShippingAddressDto shippingAddressDto) {
        ShippingAddress shippingAddress = shippingAddressRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no Shipping address found"));
        BeanUtils.copyProperties(shippingAddressDto, shippingAddress);
        ShippingAddress updatedShippingAddress = shippingAddressRepository.save(shippingAddress);
        BeanUtils.copyProperties(updatedShippingAddress, shippingAddressDto);
        return shippingAddressDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        shippingAddressRepository.deleteById(id);

    }

    @Override
    public List<ShippingAddressDto> getAll() {
        return null;
    }

    public List<ShippingAddressDto> getAllShippingAddressesByEmailAddress(String emailAddress) {
        List<ShippingAddressDto> shippingAddressDtoList = new ArrayList<>();
        shippingAddressRepository.findByEmailAddress(emailAddress).forEach(shippingAddress -> {
            ShippingAddressDto shippingAddressDto = new ShippingAddressDto();
            BeanUtils.copyProperties(shippingAddress, shippingAddressDto);
            shippingAddressDtoList.add(shippingAddressDto);
        });
        return shippingAddressDtoList;
    }


}
