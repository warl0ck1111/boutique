package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.data.entity.ProductImage;
import com.aneeque.backendservice.data.repository.ProductImageRepository;
import com.aneeque.backendservice.dto.request.ProductImageDto;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class ProductImageService implements CrudService<ProductImage, ProductImageDto> {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    @Override
    public ProductImageDto save(ProductImageDto dto) {
        ProductImage productImage = new ProductImage();
        BeanUtils.copyProperties(dto,productImage);

        ProductImage savedProductImage = productImageRepository.save(productImage);
        BeanUtils.copyProperties(savedProductImage, dto);
        return dto;

    }

    @Transactional
    @Override
    public ProductImageDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid product Image id");


        return null;
    }

    @Transactional
    @Override
    public ProductImageDto update(Long id, ProductImageDto productImageDto) {

        ProductImage productImage = productImageRepository.findById(id).orElseThrow(()-> new NoSuchElementException("no product image found"));
        BeanUtils.copyProperties(productImageDto, productImage);
        ProductImage updatedProductImage = productImageRepository.save(productImage);
        BeanUtils.copyProperties(updatedProductImage, productImageDto);
        return productImageDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productImageRepository.deleteById(id);

    }

    @Override
    public List<ProductImageDto> getAll() {
       return null;

    }


    public List<ProductResponseDto> getAllByProductId(Long productId) {
        return productImageRepository.findAllByProductId(productId);
    }

}
