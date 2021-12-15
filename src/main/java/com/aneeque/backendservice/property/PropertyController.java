package com.aneeque.backendservice.property;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse> CreateProperty(@RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(new ApiResponse("Property created successfully",propertyService.save(propertyDto)));
    }

    @PutMapping("{PropertyId}")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable Long PropertyId, @RequestBody PropertyDto propertyDto){
        return ResponseEntity.ok(new ApiResponse("Property updated successfully",propertyService.update(PropertyId, propertyDto)));
    }




    }
