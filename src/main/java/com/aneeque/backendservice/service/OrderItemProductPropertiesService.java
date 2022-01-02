package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.OrderItemProductProperties;
import com.aneeque.backendservice.data.repository.OrderItemProductPropertiesRepository;
import com.aneeque.backendservice.dto.request.OrderItemProductPropertiesDto;
import com.aneeque.backendservice.dto.response.OrderItemProductPropertiesResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Service
public class OrderItemProductPropertiesService {

    @Autowired
    private OrderItemProductPropertiesRepository orderItemProductPropertiesRepository;

    public String create(OrderItemProductPropertiesDto orderItemProductPropertiesDto) {
        OrderItemProductProperties orderItemProductProperties = new OrderItemProductProperties();
        BeanUtils.copyProperties(orderItemProductPropertiesDto, orderItemProductProperties);
        orderItemProductPropertiesRepository.save(orderItemProductProperties);

        return "saved successfully";
    }

    public List<OrderItemProductPropertiesResponseDto> getPropertiesByOrderItemId(Long orderItemId) {
        List<OrderItemProductPropertiesResponseDto> orderItemProductProperties = orderItemProductPropertiesRepository.getPropertiesByOrderItemId(orderItemId);
        return orderItemProductProperties;

    }
}
