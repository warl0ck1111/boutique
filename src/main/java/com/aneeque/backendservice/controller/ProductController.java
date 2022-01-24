package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.ProductCreateRequestDto;
import com.aneeque.backendservice.dto.request.ProductUpdateRequestDto;
import com.aneeque.backendservice.service.ProductService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    public static final String SEARCH_PRODUCT_BY_NAME = "search/{productName}";
    public static final String FIND_PRODUCT_BY_ID = "{productId}";
    public static final String CREATE_PRODUCT = "";
    public static final String UPDATE_PRODUCT = "{productId}";
    public static final String DELETE_PRODUCT = "{productId}";
    public static final String DELETE_PRODUCT_TAGS = "{productId}/tag";
    public static final String GET_ALL_PRODUCTS = "";
    @Autowired
    private ProductService productService;

    @GetMapping(GET_ALL_PRODUCTS)
    private ResponseEntity<ApiResponse> getAllProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(productService.findAllProducts(page, size)));
    }

    @GetMapping(SEARCH_PRODUCT_BY_NAME)
    private ResponseEntity<ApiResponse> searchAllProducts(@PathVariable String productName, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(new ApiResponse(productService.searchProducts(productName, page, size)));
    }

    @GetMapping(FIND_PRODUCT_BY_ID)
    private ResponseEntity<ApiResponse> findProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(new ApiResponse(productService.findProductById(productId)));
    }


    @PostMapping(CREATE_PRODUCT)
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Product created successfully", productService.createProduct(productCreateRequestDto)));
    }

    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Product updated successfully", productService.updateProduct(productId, productUpdateRequestDto)));
    }

    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully"));
    }

//    @DeleteMapping(DELETE_PRODUCT_TAGS)
//    public ResponseEntity<ApiResponse> deleteAllProductTags(@PathVariable Long productId) {
//        return ResponseEntity.ok(new ApiResponse(productService.deleteProductTag(productId)));
//    }


//    @PutMapping("{productId}/assign-properties")
//    public ResponseEntity<ApiResponse> assignPropertiesToProduct(@PathVariable Long productId, @RequestParam List<Long> propertyIds) {
//        return ResponseEntity.ok(new ApiResponse("Properties assigned to Product successfully", productService.assignPropertiesToProduct(productId, propertyIds)));
//    }
//
//    @PutMapping("{productId}/assign-attributes")
//    public ResponseEntity<ApiResponse> assignAttributesToProduct(@PathVariable Long productId, @RequestParam List<Long> attributeIds) {
//        return ResponseEntity.ok(new ApiResponse("Attributes assigned to Product successfully", productService.assignAttributesToProduct(productId, attributeIds)));
//    }



//    @DeleteMapping("{productId}/property/{propertyId}")
//    public ResponseEntity<ApiResponse> deleteProductproperty(@PathVariable Long productId, @PathVariable Long propertyId) {
//        return ResponseEntity.ok(new ApiResponse(productService.deleteProductProperty(productId, propertyId)));
//    }

//    @GetMapping("{productId}/size-info")
//    private ResponseEntity<ApiResponse> getProductSizeInformationByProductId(@PathVariable String productId) {
//        return ResponseEntity.ok(new ApiResponse(productService.getProductSizeInformationService().getProductSizeInformationByProductId(Long.valueOf(productId))));
//    }
}
