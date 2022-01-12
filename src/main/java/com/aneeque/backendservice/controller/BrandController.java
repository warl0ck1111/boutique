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
@RequestMapping("/api/vi/brands")
public class BrandController {

    public static final String UPDATE_BRAND = "{brandId}";
    public static final String GET_BRAND_BY_ID = "{brandId}";
    public static final String GET_ALL_BRANDS = "";
    public static final String DELETE_BRAND_BY_ID = "{brandId}";
    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandRequestDto brandRequestDto){
        return ResponseEntity.ok(new ApiResponse(brandService.save(brandRequestDto)));
    }

    @PutMapping(UPDATE_BRAND)
    public ResponseEntity<?> updateBrand(@PathVariable String brandId, @RequestBody BrandRequestDto brandRequestDto){
        return ResponseEntity.ok(new ApiResponse(brandService.update(Long.valueOf(brandId),brandRequestDto)));
    }

    @GetMapping(GET_BRAND_BY_ID)
    public ResponseEntity<?> getBrandById(@PathVariable String brandId){
        return ResponseEntity.ok(new ApiResponse(brandService.getById(Long.valueOf(brandId))));
    }

    @GetMapping(GET_ALL_BRANDS)
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(new ApiResponse(brandService.getAll()));
    }


    @DeleteMapping(DELETE_BRAND_BY_ID)
    public ResponseEntity<?> deleteBrandById(@PathVariable String brandId){
        return ResponseEntity.ok(new ApiResponse(brandService.delete(Long.valueOf(brandId))));
    }
}
