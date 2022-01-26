package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Cart;
import com.aneeque.backendservice.data.repository.CartRepository;
import com.aneeque.backendservice.dto.request.CartCreateRequestDto;
import com.aneeque.backendservice.dto.response.CartResponseDto;
import com.aneeque.backendservice.dto.response.CartResponseInterfaceDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public String save(CartCreateRequestDto createRequestDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(createRequestDto, cart);
        cartRepository.save(cart);
        return "cart created successfully";
    }


    public CartResponseInterfaceDto getById(Long cartId) {
        System.out.println(cartId);
        CartResponseInterfaceDto cart = cartRepository.findByCartId(cartId).orElseThrow(() -> new NoSuchElementException("cart not found"));
        System.out.println(cart);
        return cart;
    }


    @Transactional
    public String updateQuantity(Long id,Long quantity) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new NoSuchElementException("cart not found"));
        cart.setQuantity(quantity);
        cartRepository.save(cart);
        return "cart quantity updated successfully";
    }

    public Set<CartResponseDto> getByCreatorId(Long creatorId) {
        List<CartResponseInterfaceDto> cartResponseInterfaceDtoList = cartRepository.findByCreatorId(creatorId);
        Set<CartResponseDto> cartResponseDtoList= new HashSet<>();
        cartResponseInterfaceDtoList.forEach(x->{
            CartResponseDto cartResponseDto = new CartResponseDto();
            BeanUtils.copyProperties(x,cartResponseDto);
            cartResponseDtoList.add(cartResponseDto);
        });

        //mapping list of file names
        cartResponseDtoList.forEach(x->{
            cartResponseInterfaceDtoList.forEach(y->{
                if(x.getUniqueId() == y.getUniqueId()){
                    x.getFileNames().add(y.getFileName());
                }
            });
        });
        return cartResponseDtoList;
    }


    public Set<CartResponseDto> getByUniqueId(String uniqueId) {
        List<CartResponseInterfaceDto> cartResponseInterfaceDtoList = cartRepository.findByUniqueId(uniqueId);
        Set<CartResponseDto> cartResponseDtoList= new HashSet<>();

        cartResponseInterfaceDtoList.forEach(x->{
            CartResponseDto cartResponseDto = new CartResponseDto();
            BeanUtils.copyProperties(x,cartResponseDto);
            cartResponseDto.setId(x.getCartId());
            cartResponseDtoList.add(cartResponseDto);
        });

        //mapping list of file names
        cartResponseDtoList.forEach(x->{
            cartResponseInterfaceDtoList.forEach(y->{
                if(x.getUniqueId() == y.getUniqueId()){
                    x.getFileNames().add(y.getFileName());
                }
            });
        });
        return cartResponseDtoList;
    }


    public void delete(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}
