package com.aneeque.backendservice.service;


import com.aneeque.backendservice.data.entity.CartProductProperties;
import com.aneeque.backendservice.data.repository.CartProductPropertiesRepository;
import com.aneeque.backendservice.dto.request.CartProductPropertyDto;
import com.aneeque.backendservice.dto.response.CartProductPropertiesDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Service
public class CartProductPropertiesService {

    @Autowired
    private CartProductPropertiesRepository cartProductPropertiesRepository;

    public CartProductPropertyDto save(CartProductPropertyDto cartProductPropertiesDto){
        CartProductProperties cartProductProperties = new CartProductProperties();
        BeanUtils.copyProperties(cartProductPropertiesDto, cartProductProperties);

        CartProductProperties savedCartProductProperties = cartProductPropertiesRepository.save(cartProductProperties);
        BeanUtils.copyProperties(savedCartProductProperties, cartProductPropertiesDto);

        return cartProductPropertiesDto;
    }

    public List<CartProductPropertiesDto> getCartProductPropertyById(Long cartId) {
        List<CartProductPropertiesDto> property = cartProductPropertiesRepository.getPropertyByCartId(cartId);
        return property;
    }
}
