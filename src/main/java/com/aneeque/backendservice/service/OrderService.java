package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Order;
import com.aneeque.backendservice.data.repository.OrderRepository;
import com.aneeque.backendservice.dto.request.OrderDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDto save(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        Order savedOrder = orderRepository.save(order);
        BeanUtils.copyProperties(savedOrder, orderDto);
        return orderDto;
    }


    public OrderDto getById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("no order found"));
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }


    public OrderDto updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("no order found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(updatedOrder, orderDto);
        return orderDto;
    }

    public void delete(Long orderId){
        orderRepository.deleteById(orderId);
    }

    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.forEach(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            orderDtoList.add(orderDto);
        });
        return orderDtoList;
    }



}
