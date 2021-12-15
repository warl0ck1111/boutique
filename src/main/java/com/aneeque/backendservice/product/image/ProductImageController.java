package com.aneeque.backendservice.product.image;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/product-image")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("{productImageId}")
    public ResponseEntity<?> getProductImage(@PathVariable Long productImageId){
        return ResponseEntity.ok(new ApiResponse(  productImageService.getById(productImageId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveProductImage(@Valid @RequestBody ProductImageDto productImageDto){
        return ResponseEntity.ok(new ApiResponse("product image created successfully",productImageService.save(productImageDto)));

    }

    @PutMapping("{productImageId}")
    public ResponseEntity<ApiResponse> updateProductImage(@PathVariable Long productImageId, @Valid @RequestBody ProductImageDto productImageDto){
        return ResponseEntity.ok(new ApiResponse("product image updated successfully",productImageService.update(productImageId, productImageDto)));
    }

    @DeleteMapping("{productImageId}")
    public ResponseEntity<ApiResponse> deleteProductImage(@PathVariable Long productImageId){
        productImageService.delete(productImageId);
        return ResponseEntity.ok(new ApiResponse("product image deleted successfully"));
    }
}
