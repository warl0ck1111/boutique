package com.aneeque.backendservice.category.department.attribute;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new ApiResponse(attributeService.save(attributeDto)));
    }

    @PutMapping("{attributeId}")
    public ResponseEntity<ApiResponse> updateAttribute(@PathVariable Long attributeId, @RequestBody AttributeDto attributeDto) {
        return ResponseEntity.ok(new ApiResponse(attributeService.update(attributeId,attributeDto)));
    }

}
