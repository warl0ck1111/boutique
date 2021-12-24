package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Cart;
import com.aneeque.backendservice.data.repository.CartRepository;
import com.aneeque.backendservice.dto.request.CartCreateRequestDto;
import com.aneeque.backendservice.dto.response.CartResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public CartResponseDto save(CartCreateRequestDto createRequestDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(createRequestDto, cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(savedCart, cartResponseDto);

        return cartResponseDto;
    }

    
    public CartResponseDto getById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new NoSuchElementException("cart not found"));
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(cartResponseDto, cart);
        return cartResponseDto;
    }


    
    public CartResponseDto updateQuantity(Long id,Long quantity) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new NoSuchElementException("cart nt found"));
        cart.setQuantity(quantity);
        Cart updatedCart = cartRepository.save(cart);
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(updatedCart, cartResponseDto);
        return cartResponseDto;
    }

    public CartResponseDto getByCreatorId(Long creatorId) {
        Cart cart = cartRepository.findByCreatorId(creatorId).orElseThrow(()-> new NoSuchElementException("no cart found"));
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(cartResponseDto, cart);
        return cartResponseDto;
    }


    public CartResponseDto getByUniqueId(String uniqueId) {
        Cart cart = cartRepository.findByUniqueId(uniqueId).orElseThrow(()-> new NoSuchElementException("no cart found"));
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(cartResponseDto, cart);
        return cartResponseDto;
    }


    public void delete(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}
