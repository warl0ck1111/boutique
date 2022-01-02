package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.dto.request.ShippingAddressDto;
import com.aneeque.backendservice.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */
@RestController
@RequestMapping("/api/v1/shipping-address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping
    public ResponseEntity<?> createShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto){
        return ResponseEntity.ok(new ApiResponse("shipping address created successfully", shippingAddressService.save(shippingAddressDto)));
    }

    @PutMapping("{shippingAddressId}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable String shippingAddressId, @Valid @RequestBody ShippingAddressDto shippingAddressDto){
        return ResponseEntity.ok(new ApiResponse("shipping address updated successfully", shippingAddressService.update(Long.valueOf(shippingAddressId),shippingAddressDto)));
    }

    @GetMapping
    public ResponseEntity<?> getShippingAddressByEmail(@RequestParam String emailAddress){
        return ResponseEntity.ok(new ApiResponse( shippingAddressService.getAllShippingAddressesByEmailAddress(emailAddress)));
    }



}
