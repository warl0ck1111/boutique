package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.CheckoutDTO;
import com.aneeque.backendservice.dto.request.OrderDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Okala Bashir .O.
 */


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResponse> getOrders() {
        return ResponseEntity.ok(new ApiResponse(orderService.getAllOrders()));
    }

    @GetMapping("{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable String orderId) {
        return ResponseEntity.ok(new ApiResponse(orderService.getById(Long.valueOf(orderId))));
    }

    @GetMapping("email/{emailAddress}")
    public ResponseEntity<ApiResponse> getOrderByEmailAddress(@PathVariable String emailAddress) {
        return ResponseEntity.ok(new ApiResponse(orderService.getByOrderByEmailAddress(emailAddress)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(new ApiResponse("order created successfully", orderService.save(orderDto)));
    }

    @PostMapping("checkout")
    public ResponseEntity<ApiResponse> checkout(@RequestBody CheckoutDTO checkoutDTO) {
        return ResponseEntity.ok(new ApiResponse("order created successfully", orderService.checkout(checkoutDTO)));
    }

    @PutMapping("{orderId}")
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        return ResponseEntity.ok(new ApiResponse("order status updated successfully", orderService.updateStatus(Long.valueOf(orderId), status)));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId) {
        orderService.delete(Long.valueOf(orderId));
        return ResponseEntity.ok(new ApiResponse("order deleted successfully"));
    }

}
