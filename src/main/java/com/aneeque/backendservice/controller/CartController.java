package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.CartCreateRequestDto;
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


    @PostMapping()
    public ResponseEntity<ApiResponse> createCartItem( @Valid @RequestBody CartCreateRequestDto createRequestDto){
        return ResponseEntity.ok(new ApiResponse("cart created successfully",cartService.save(createRequestDto)));
    }

    @PutMapping("{cartId}")
     public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartId, @RequestParam Long quantity){
        return ResponseEntity.ok(new ApiResponse("cart updated successfully",cartService.updateQuantity(cartId, quantity)));
    }

    @DeleteMapping("{cartId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartId){
        cartService.delete(cartId);
        return ResponseEntity.ok(new ApiResponse("cart deleted successfully"));
    }

    @GetMapping("{uniqueId}")
    public ResponseEntity<?> getAllCartItemsByUniqueId(@PathVariable String uniqueId){
        return ResponseEntity.ok(new ApiResponse(cartService.getByUniqueId(uniqueId)));
    }


}
