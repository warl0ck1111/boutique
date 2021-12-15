package com.aneeque.backendservice.product;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity<ApiResponse> getAllProducts() {
        return ResponseEntity.ok(new ApiResponse(productService.getAll()));
    }

    @GetMapping("{productId}")
    private ResponseEntity<ApiResponse> getAllByProperties(@PathVariable Long productId) {
        return ResponseEntity.ok(new ApiResponse(productService.getAllByProperties(productId)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(new ApiResponse("Product created successfully", productService.save(productDto)));
    }

    @PutMapping("{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(new ApiResponse("Product updated successfully", productService.update(productId, productDto)));
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
}
