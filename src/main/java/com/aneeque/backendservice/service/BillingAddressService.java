package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.BillingAddress;
import com.aneeque.backendservice.data.repository.BillingAddressRepository;
import com.aneeque.backendservice.dto.request.BillingAddressDto;
import com.aneeque.backendservice.dto.request.ShippingAddressDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
