package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.BrandRequestDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/vi/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandRequestDto brandRequestDto){
        return ResponseEntity.ok(new ApiResponse(brandService.save(brandRequestDto)));
    }

    @PutMapping("{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable String brandId, @RequestBody BrandRequestDto brandRequestDto){
        return ResponseEntity.ok(new ApiResponse(brandService.update(Long.valueOf(brandId),brandRequestDto)));
    }

    @GetMapping("{brandId}")
    public ResponseEntity<?> getBrandById(@PathVariable String brandId){
        return ResponseEntity.ok(new ApiResponse(brandService.getById(Long.valueOf(brandId))));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(new ApiResponse(brandService.getAll()));
    }


    @DeleteMapping("{brandId}")
    public ResponseEntity<?> deleteTagById(@PathVariable String brandId){
        return ResponseEntity.ok(new ApiResponse(brandService.delete(Long.valueOf(brandId))));
    }
}
