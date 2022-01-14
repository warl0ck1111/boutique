package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.AttributeRequestDto;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Okala III
 */

@RestController
@RequestMapping("/api/v1/attributes")
public class AttributeController {

    @Autowired
    private AttributeServiceImpl attributeService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAttribute() {
        return ResponseEntity.ok(new ApiResponse(attributeService.getAllAttributes()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAttribute(@RequestBody @Valid AttributeRequestDto attributeRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Attribute created successfully", attributeService.save(attributeRequestDto)));
    }

    @PutMapping("{attributeId}")
    public ResponseEntity<ApiResponse> updateAttribute(@PathVariable Long attributeId, @Valid @RequestBody AttributeRequestDto attributeRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Attribute updated successfully", attributeService.update(attributeId, attributeRequestDto)));
    }

    @PutMapping("{attributeId}/properties")
    public ResponseEntity<ApiResponse> assignPropertiesToAttribute(@PathVariable Long attributeId,  @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Attribute updated successfully", attributeService.assignPropertiesToAttribute(attributeId, properties)));
    }

    @GetMapping("{attributeId}")
    public ResponseEntity<ApiResponse> getAttribute(@PathVariable Long attributeId) {
        return ResponseEntity.ok(new ApiResponse(attributeService.findAttributeById(attributeId)));
    }

    @GetMapping("{attributeId}/property")
    public ResponseEntity<ApiResponse> getAssignedPropertiesToAttribute(@PathVariable Long attributeId) {
        return ResponseEntity.ok(new ApiResponse(attributeService.getAssignedPropertiesToAttribute(attributeId)));
    }


}
