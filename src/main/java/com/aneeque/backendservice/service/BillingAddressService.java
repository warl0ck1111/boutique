package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.BillingAddress;
import com.aneeque.backendservice.data.repository.BillingAddressRepository;
import com.aneeque.backendservice.dto.request.BillingAddressDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
