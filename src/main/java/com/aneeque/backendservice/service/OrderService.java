package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Cart;
import com.aneeque.backendservice.data.entity.Order;
import com.aneeque.backendservice.data.repository.OrderRepository;
import com.aneeque.backendservice.dto.request.*;
import com.aneeque.backendservice.dto.response.CartResponseDto;
import com.aneeque.backendservice.dto.response.OrderResponseDTO;
import com.aneeque.backendservice.dto.response.OrderResponseDtoInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Okala Bashir .O.
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ShippingAddressService shippingAddressService;

    @Autowired
    BillingAddressService billingAddressService;


    @Transactional
    public CheckoutDTO checkout(CheckoutDTO checkoutDTO) {

        // get cart items
        Set<CartResponseDto> cartItems = cartService.getByUniqueId(checkoutDTO.getUniqueId());
        //get total amount
        //Double sumTotalAmount = cartItems.stream().mapToDouble(CartResponseDto::getTotalAmount).sum();
        // get shippingAddress info
        ShippingAddressDto shippingAddressDto = null;
        if(Objects.nonNull(checkoutDTO.getShippingAddressDetail()) && checkoutDTO.getShippingAddressDetail().getId() != null && checkoutDTO.getShippingAddressDetail().getId() > 0){
            shippingAddressDto = shippingAddressService.getById(checkoutDTO.getShippingAddressDetail().getId());
        }else {
            shippingAddressDto = shippingAddressService.save(checkoutDTO.getShippingAddressDetail());
        }

        // get billingAddress info
        BillingAddressDto billingAddressDto = null;
        if(Objects.nonNull(checkoutDTO.getBillingAddressDetail()) && checkoutDTO.getBillingAddressDetail().getId()!= null && checkoutDTO.getBillingAddressDetail().getId() > 0){
            billingAddressDto = billingAddressService.getById(checkoutDTO.getBillingAddressDetail().getId());
        }else {
            billingAddressDto = billingAddressService.createBillingAddress(checkoutDTO.getBillingAddressDetail());
        }
        Long billingAddressId = billingAddressDto.getId();
        Long shippingAddressId = shippingAddressDto.getId();


        //Create order record and get the id = orderId
        Order order = new Order();
        BeanUtils.copyProperties(checkoutDTO, order);
        order.setBillingAddressId(billingAddressId);
        order.setShippingAddressId(shippingAddressId);
        order.setStatus("PROCESSING");
        order.setItemCount(cartItems.size());
        order.setUniqueId(checkoutDTO.getUniqueId());
        order.setPaymentMethod(checkoutDTO.getPaymentMethod());
        order.setEmailAddress(checkoutDTO.getEmailAddress());
        Order savedOrder = orderRepository.save(order);
        BeanUtils.copyProperties(savedOrder, checkoutDTO);

        Long orderId = savedOrder.getId();

        //Loop through cart items and create the order Items using the orderId
        for(CartResponseDto item : cartItems){
            CartCreateRequestDto cartCreateRequestDto = new CartCreateRequestDto();
            cartCreateRequestDto.setQuantity(item.getQuantity());
            cartCreateRequestDto.setProductId(item.getProductId());
            cartCreateRequestDto.setUniqueId(item.getUniqueId());
            cartCreateRequestDto.setOrderId(orderId);
            cartService.save(cartCreateRequestDto);
        }
        return checkoutDTO;
    }

    @Transactional
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

    public List<OrderDto> getByOrderByEmailAddress(String emailAddress) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderRepository.findByEmailAddress(emailAddress);
        orderList.forEach(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);
            orderDtoList.add(orderDto);

        });
        return orderDtoList;
    }


    @Transactional
    public OrderDto updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("no order found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(updatedOrder, orderDto);
        return orderDto;
    }

    @Transactional
    public void delete(Long orderId){
        orderRepository.deleteById(orderId);
    }

    public List<OrderResponseDtoInterface> getAllOrders(){
        List<OrderResponseDtoInterface> orders = orderRepository.getAllOrders();

        return orders;
    }



}
