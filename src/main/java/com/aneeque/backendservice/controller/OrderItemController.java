package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.OrderItemDto;
import com.aneeque.backendservice.dto.request.OrderItemProductPropertiesDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.OrderItemService;
import com.aneeque.backendservice.service.OrderItemProductPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemProductPropertiesService orderItemProductPropertiesService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getOrderItemsByOrderId(@RequestParam String orderId) {
        return ResponseEntity.ok(new ApiResponse(orderItemService.getByOrderItemsByOrderId(orderId)));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto){
        return ResponseEntity.ok(new ApiResponse("order item created successfully",orderItemService.save(orderItemDto)));
    }

    @PutMapping("{orderItemId}")
    public ResponseEntity<ApiResponse> updateOrderItemStatus(@PathVariable Long orderItemId, @RequestParam String status){
        return ResponseEntity.ok(new ApiResponse("order item status updated successfully",orderItemService.updateStatus(orderItemId,status)));
    }

    @PostMapping("product-properties")
    public ResponseEntity<ApiResponse> createOrderItemProductProperty(@Valid @RequestBody OrderItemProductPropertiesDto orderItemProductPropertiesDto){
        return ResponseEntity.ok(new ApiResponse("order item product property created successfully",orderItemProductPropertiesService.create(orderItemProductPropertiesDto)));
    }

    @GetMapping("product-properties")
    public ResponseEntity<ApiResponse> getPropertiesByOrderItemId(@RequestParam String orderItemId) {
        System.out.println(orderItemId);
        return ResponseEntity.ok(new ApiResponse(orderItemProductPropertiesService.getPropertiesByOrderItemId(Long.valueOf(orderItemId))));
    }

}
