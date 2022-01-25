package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.BillingAddress;
import com.aneeque.backendservice.data.entity.ShippingAddress;
import com.aneeque.backendservice.data.repository.BillingAddressRepository;
import com.aneeque.backendservice.dto.request.BillingAddressDto;
import com.aneeque.backendservice.dto.request.ShippingAddressDto;
import com.aneeque.backendservice.exception.ApiRequestException;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Okala Bashir .O.
 */

@Service
@Getter
public class BillingAddressService {

    @Autowired
    private BillingAddressRepository billingAddressRepository;


    public BillingAddressDto createBillingAddress(BillingAddressDto billingAddressDto){
        BillingAddress billingAddress = new BillingAddress();
        BeanUtils.copyProperties(billingAddressDto, billingAddress);
        BillingAddress savedBillingAddress = billingAddressRepository.save(billingAddress);
        BeanUtils.copyProperties(savedBillingAddress, billingAddressDto);
        return billingAddressDto;

    }

    public List<BillingAddressDto> getBillingAddressByEmailAddress(String emailAddress){
        List<BillingAddressDto> billingAddressDtoList = new ArrayList<>();
        billingAddressRepository.findByEmailAddress(emailAddress)
        .forEach(billingAddress -> {
            BillingAddressDto billingAddressDto = new BillingAddressDto();
            BeanUtils.copyProperties(billingAddress, billingAddressDto);
            billingAddressDtoList.add(billingAddressDto);
        });
        return billingAddressDtoList;
    }

    public BillingAddressDto getById(Long id) {

        BillingAddress billingAddress = billingAddressRepository.getById(id);
        if(Objects.isNull(billingAddress)){
            throw new ApiRequestException("Billing Address data does not exist", HttpStatus.BAD_REQUEST);
        }
        BillingAddressDto response = new BillingAddressDto();
        BeanUtils.copyProperties(billingAddress, response);
        return response;
    }
}
