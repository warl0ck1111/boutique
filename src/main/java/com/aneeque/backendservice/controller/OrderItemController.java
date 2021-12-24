package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.OrderItemDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<ApiResponse> getOrderItems() {
        return ResponseEntity.ok(new ApiResponse(orderItemService.getAll()));
    }

    @GetMapping("{orderItemId}")
    public ResponseEntity<?> getOrderItem(@PathVariable Long orderItemId) {
        return ResponseEntity.ok(new ApiResponse(orderItemService.getById(orderItemId)));
    }
    
    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(new ApiResponse("order item created successfully",orderItemService.save(orderItemDto)));
    }

    @PutMapping("{orderItemId}")
    public ResponseEntity<?> createOrderItem(@PathVariable Long orderItemId, @RequestParam String status){
        return ResponseEntity.ok(new ApiResponse("order item status updated successfully",orderItemService.updateStatus(orderItemId,status)));
    }

    @DeleteMapping("{orderItemId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderItemId) {
        orderItemService.delete(orderItemId);
        return ResponseEntity.ok(new ApiResponse("order item deleted successfully"));
    }

}
