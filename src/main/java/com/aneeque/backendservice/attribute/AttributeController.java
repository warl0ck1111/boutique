package com.aneeque.backendservice.attribute;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Okala III
 */

@RestController
@RequestMapping("/api/v1")
public class AttributeController {

    @Autowired
    private AttributeServiceImpl attributeService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAttribute() {
        return ResponseEntity.ok(new ApiResponse(attributeService.getAllAttributes()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAttribute(@RequestBody AttributeDto attributeDto) {
        return ResponseEntity.ok(new ApiResponse("Attribute created successfully", attributeService.save(attributeDto)));
    }

    @PutMapping("{attributeId}")
    public ResponseEntity<ApiResponse> updateAttribute(@PathVariable Long attributeId, @RequestBody AttributeDto attributeDto) {
        return ResponseEntity.ok(new ApiResponse("Attribute updated successfully", attributeService.update(attributeId, attributeDto)));
    }

    @PutMapping("{attributeId}/assign/properties")
    public ResponseEntity<ApiResponse> assignPropertiesToAttribute(@PathVariable Long attributeId, @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Attribute updated successfully", attributeService.assignPropertiesToAttribute(attributeId, properties)));
    }

    @GetMapping("{attributeId}/get-assigned-property")
    public ResponseEntity<ApiResponse> getAssignedPropertiesToAttribute(@PathVariable Long attributeId) {
        return ResponseEntity.ok(new ApiResponse(attributeService.getAssignedPropertiesToAttribute(attributeId)));
    }


}
