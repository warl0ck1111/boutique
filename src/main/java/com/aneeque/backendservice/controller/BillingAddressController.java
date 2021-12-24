package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.BillingAddressDto;
import com.aneeque.backendservice.service.BillingAddressService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/billing-address")
public class BillingAddressController {

    @Autowired
    private BillingAddressService billingAddressService;

    @PostMapping
    public ResponseEntity<?> createBillingAddress(@RequestBody BillingAddressDto billingAddressDto){
        return ResponseEntity.ok(new ApiResponse("billing address created successfully", billingAddressService.createBillingAddress(billingAddressDto)));
    }


}
