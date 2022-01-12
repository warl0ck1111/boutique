package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.service.CartProductPropertiesService;
import com.aneeque.backendservice.dto.request.CartCreateRequestDto;
import com.aneeque.backendservice.dto.request.CartProductPropertyDto;
import com.aneeque.backendservice.service.CartService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */
@RestController
@RequestMapping("/api/v1/cart/")
public class CartController {


    public static final String CREATE_CART = "";
    public static final String UPDATE_CART_QUANTITY = "{cartId}";
    public static final String DELETE_CART = "{cartId}";
    public static final String GET_ALL_CART_BY_UNIQUE_ID = "unique-id/{uniqueId}";
    public static final String GET_CART_BY_ID = "{cartId}";
    public static final String FIND_CART_BY_CREATOR_ID = "creator/{creatorId}";
    public static final String GET_PROPERTY_BY_CART_ID = "{cartId}/property";
    public static final String SAVE_PRODUCT_PROPERTY = "product-property";
    public static final String SAVE_CART_PRODUCT_PROPERTY = "product-property";
    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductPropertiesService cartProductPropertiesService;


    @PostMapping(CREATE_CART)
    public ResponseEntity<ApiResponse> createCart(@Valid @RequestBody CartCreateRequestDto createRequestDto) {
        return ResponseEntity.ok(new ApiResponse("cart created successfully", cartService.save(createRequestDto)));
    }

    @PutMapping(UPDATE_CART_QUANTITY)
    public ResponseEntity<ApiResponse> updateQuantity(@PathVariable Long cartId, @RequestParam Long quantity) {
        return ResponseEntity.ok(new ApiResponse("cart updated successfully", cartService.updateQuantity(cartId, quantity)));
    }

    @DeleteMapping(DELETE_CART)
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long cartId) {
        cartService.delete(cartId);
        return ResponseEntity.ok(new ApiResponse("cart deleted successfully"));
    }

    @GetMapping(GET_ALL_CART_BY_UNIQUE_ID)
    public ResponseEntity<?> getAllCartByUniqueId(@PathVariable String uniqueId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getByUniqueId(uniqueId)));
    }

    @GetMapping(GET_CART_BY_ID)
    public ResponseEntity<?> findCartById(@PathVariable String cartId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getById(Long.valueOf(cartId))));
    }

    @GetMapping(FIND_CART_BY_CREATOR_ID)
    public ResponseEntity<?> findCartByCreatorId(@PathVariable String creatorId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getByCreatorId(Long.valueOf(creatorId))));
    }


    @GetMapping(GET_PROPERTY_BY_CART_ID)
    public ResponseEntity<?> getPropertiesByCartId(@PathVariable String cartId) {
        return ResponseEntity.ok(new ApiResponse(cartProductPropertiesService.getCartProductPropertyById(Long.valueOf(cartId))));
    }


    @PostMapping(SAVE_CART_PRODUCT_PROPERTY)
    public ResponseEntity<?> saveCartProductProperty(@RequestBody CartProductPropertyDto cartProductPropertyDto){
        return ResponseEntity.ok(new ApiResponse(cartProductPropertiesService.save(cartProductPropertyDto)));
    }


}
