package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.OrderItem;
import com.aneeque.backendservice.data.repository.OrderItemRepository;
import com.aneeque.backendservice.dto.request.OrderItemDto;
import com.aneeque.backendservice.dto.response.OrderItemResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Service
public class OrderItemService{
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItemDto save(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemDto,orderItem);
        OrderItem savedOrder = orderItemRepository.save(orderItem);
        BeanUtils.copyProperties(savedOrder, orderItemDto);
        return orderItemDto;
    }

    
    public List<OrderItemResponseDto> getByOrderItemsByOrderId(String orderId) {
        List<OrderItemResponseDto> orderItems = orderItemRepository.findOrderItemsByOrderId(orderId);
        return orderItems;
    }

    @Transactional
    public OrderItemDto updateStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new NoSuchElementException("no order item found"));
        orderItem.setStatus(status);
        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(updatedOrderItem, orderItemDto);
        return orderItemDto;    }

    @Transactional
    public void delete(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    
    public List<OrderItemDto> getAll() {
        return null;
    }
}
