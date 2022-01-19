package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.dto.request.ProductMediaDto;
import com.aneeque.backendservice.service.ProductMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/product-media")
public class ProductMediaController {
    public static final String DELETE_PRODUCT_MEDIA = "{productMediaId}";
    public static final String UPDATE_PRODUCT_MEDIA = "{productMediaId}";
    public static final String CREATE_PRODUCT_MEDIA = "";
    public static final String GET_ALL_PRODUCT_MEDIA = "{productMediaId}";
    @Autowired
    private ProductMediaService productMediaService;

    @GetMapping(GET_ALL_PRODUCT_MEDIA)
    public ResponseEntity<?> getAllProductMedia(@PathVariable Long productMediaId){
        return ResponseEntity.ok(new ApiResponse(  productMediaService.getById(productMediaId)));
    }

    @PostMapping(CREATE_PRODUCT_MEDIA)
    public ResponseEntity<ApiResponse> saveProductMedia(@Valid @RequestBody ProductMediaDto productMediaDto){
        return ResponseEntity.ok(new ApiResponse("product media created successfully", productMediaService.save(productMediaDto)));

    }

    @PutMapping(UPDATE_PRODUCT_MEDIA)
    public ResponseEntity<ApiResponse> updateProductMedia(@PathVariable Long productMediaId, @Valid @RequestBody ProductMediaDto productMediaDto){
        return ResponseEntity.ok(new ApiResponse("product media updated successfully", productMediaService.update(productMediaId, productMediaDto)));
    }

    @DeleteMapping(DELETE_PRODUCT_MEDIA)
    public ResponseEntity<ApiResponse> deleteProductMedia(@PathVariable Long productMediaId){
        productMediaService.delete(productMediaId);
        return ResponseEntity.ok(new ApiResponse("product media deleted successfully"));
    }
}
