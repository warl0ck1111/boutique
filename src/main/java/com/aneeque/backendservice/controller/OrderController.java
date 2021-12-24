package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.OrderDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Okala Bashir .O.
 */
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResponse> getOrders() {
        return ResponseEntity.ok(new ApiResponse(orderService.getAllOrders()));
    }

    @GetMapping("orderId")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(new ApiResponse(orderService.getById(orderId)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(new ApiResponse("order created successfully", orderService.save(orderDto)));
    }

    @PutMapping("{orderId}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long orderId, @RequestParam String status) {
        return ResponseEntity.ok(new ApiResponse("order status updated successfully", orderService.updateStatus(orderId, status)));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok(new ApiResponse("order deleted successfully"));
    }

}
