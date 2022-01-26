package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Cart;
import com.aneeque.backendservice.data.repository.CartRepository;
import com.aneeque.backendservice.dto.request.CartCreateRequestDto;
import com.aneeque.backendservice.dto.response.CartResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public CartResponseDto save(CartCreateRequestDto createRequestDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(createRequestDto, cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(savedCart, cartResponseDto);

        return cartResponseDto;
    }


    public CartResponseDto getById(Long cartId) {
        System.out.println(cartId);
        CartResponseDto cart = cartRepository.findByCartId(cartId).orElseThrow(() -> new NoSuchElementException("cart not found"));
        System.out.println(cart);
        return cart;
    }


    @Transactional
    public CartResponseDto updateQuantity(Long id,Long quantity) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new NoSuchElementException("cart not found"));
        cart.setQuantity(quantity);
        Cart updatedCart = cartRepository.save(cart);
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(updatedCart, cartResponseDto);
        return cartResponseDto;
    }

    public List<CartResponseDto> getByCreatorId(Long creatorId) {
        List<CartResponseDto> cart = cartRepository.findByCreatorId(creatorId);
        System.out.println(cart);
        return cart;
    }


    public List<Cart> getByUniqueId(String uniqueId) {
        List<Cart> cart = cartRepository.findByUniqueId(uniqueId);
        System.out.println(cart);
        return cart;
    }


    public void delete(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}
