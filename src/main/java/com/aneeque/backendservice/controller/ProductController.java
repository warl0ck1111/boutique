package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.ProductCreateRequestDto;
import com.aneeque.backendservice.service.ProductService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    private ResponseEntity<ApiResponse> getAllProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(productService.getAllProducts(page, size)));
    }

    @GetMapping("{productId}")
    private ResponseEntity<ApiResponse> findProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(new ApiResponse(productService.findProductById(productId)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Product created successfully", productService.createProduct(productCreateRequestDto)));
    }

    @PutMapping("{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Product updated successfully", productService.updateProduct(productId, productCreateRequestDto)));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully"));
    }

    @PutMapping("{productId}/assign-properties")
    public ResponseEntity<ApiResponse> assignPropertiesToProduct(@PathVariable Long productId, @RequestParam List<Long> propertyIds) {
        return ResponseEntity.ok(new ApiResponse("Properties assigned to Product successfully", productService.assignPropertiesToProduct(productId, propertyIds)));
    }

    @PutMapping("{productId}/assign-attributes")
    public ResponseEntity<ApiResponse> assignAttributesToProduct(@PathVariable Long productId, @RequestParam List<Long> attributeIds) {
        return ResponseEntity.ok(new ApiResponse("Attributes assigned to Product successfully", productService.assignAttributesToProduct(productId, attributeIds)));
    }

    @DeleteMapping("{productId}/tag/{tagName}")
    public ResponseEntity<ApiResponse> deleteProductTag(@PathVariable Long productId, @PathVariable String tagName) {
        return ResponseEntity.ok(new ApiResponse(productService.deleteProductTag(productId, tagName)));
    }

//    @DeleteMapping("{productId}/property/{propertyId}")
//    public ResponseEntity<ApiResponse> deleteProductproperty(@PathVariable Long productId, @PathVariable Long propertyId) {
//        return ResponseEntity.ok(new ApiResponse(productService.deleteProductProperty(productId, propertyId)));
//    }

    @GetMapping("{productId}/size-info")
    private ResponseEntity<ApiResponse> getProductSizeInformationByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(new ApiResponse(productService.getProductSizeInformationService().getProductSizeInformationByProductId(Long.valueOf(productId))));
    }
}
