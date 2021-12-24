package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.OrderItem;
import com.aneeque.backendservice.data.repository.OrderItemRepository;
import com.aneeque.backendservice.dto.request.OrderItemDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Service
public class OrderItemService{
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    
    public OrderItemDto save(OrderItemDto dto) {
        return null;
    }

    
    public OrderItemDto getById(Long id) {
        return null;
    }

    
    public OrderItemDto updateStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new NoSuchElementException("no order item found"));
        orderItem.setStatus(status);
        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(updatedOrderItem, orderItemDto);
        return orderItemDto;    }

    
    public void delete(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);

    }

    
    public List<OrderItemDto> getAll() {
        return null;
    }
}
