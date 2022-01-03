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


    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductPropertiesService cartProductPropertiesService;


    @PostMapping()
    public ResponseEntity<ApiResponse> saveCart(@Valid @RequestBody CartCreateRequestDto createRequestDto) {
        return ResponseEntity.ok(new ApiResponse("cart created successfully", cartService.save(createRequestDto)));
    }

    @PutMapping("{cartId}")
    public ResponseEntity<ApiResponse> updateQuantity(@PathVariable Long cartId, @RequestParam Long quantity) {
        return ResponseEntity.ok(new ApiResponse("cart updated successfully", cartService.updateQuantity(cartId, quantity)));
    }

    @DeleteMapping("{cartId}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long cartId) {
        cartService.delete(cartId);
        return ResponseEntity.ok(new ApiResponse("cart deleted successfully"));
    }

    @GetMapping("unique-id/{uniqueId}")
    public ResponseEntity<?> getAllCartByUniqueId(@PathVariable String uniqueId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getByUniqueId(uniqueId)));
    }

    @GetMapping("{cartId}")
    public ResponseEntity<?> findCartById(@PathVariable String cartId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getById(Long.valueOf(cartId))));
    }

    @GetMapping("creator/{creatorId}")
    public ResponseEntity<?> findCartByCreatorId(@PathVariable String creatorId) {
        return ResponseEntity.ok(new ApiResponse(cartService.getByCreatorId(Long.valueOf(creatorId))));
    }


    @GetMapping("{cartId}/property")
    public ResponseEntity<?> getPropertiesByCartId(@PathVariable String cartId) {
        return ResponseEntity.ok(new ApiResponse(cartProductPropertiesService.getCartProductPropertyById(Long.valueOf(cartId))));
    }


    @PostMapping("product-property")
    public ResponseEntity<?> saveCartProductProperty(@RequestBody CartProductPropertyDto cartProductPropertyDto){
        return ResponseEntity.ok(new ApiResponse(cartProductPropertiesService.save(cartProductPropertyDto)));
    }


}
