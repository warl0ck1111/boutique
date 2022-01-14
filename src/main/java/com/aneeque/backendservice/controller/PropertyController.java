package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.PropertyRequestDto;
import com.aneeque.backendservice.service.PropertyService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllProperties(){
        return ResponseEntity.ok(new ApiResponse(propertyService.getAll()));

    }

    @GetMapping("{propertyId}")
    public ResponseEntity<ApiResponse> getProperty(@PathVariable Long propertyId){
        return ResponseEntity.ok(new ApiResponse(propertyService.getById(propertyId)));

    }

    @PostMapping()
    public ResponseEntity<ApiResponse> CreateProperty(@Valid @RequestBody PropertyRequestDto propertyDto){
        return ResponseEntity.ok(new ApiResponse("Property created successfully",propertyService.save(propertyDto)));
    }

    @PutMapping("{PropertyId}")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable Long PropertyId, @Valid @RequestBody PropertyRequestDto propertyDto){
        return ResponseEntity.ok(new ApiResponse("Property updated successfully",propertyService.update(PropertyId, propertyDto)));
    }




    }
