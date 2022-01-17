package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Product;
import com.aneeque.backendservice.data.entity.ProductImage;
import com.aneeque.backendservice.data.repository.ProductImageRepository;
import com.aneeque.backendservice.dto.request.ProductImageDto;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("creating product image");
        ProductImage productImage = new ProductImage();
        BeanUtils.copyProperties(dto, productImage);

        ProductImage savedProductImage = productImageRepository.save(productImage);
        BeanUtils.copyProperties(savedProductImage, dto);
        return dto;

    }

    @Transactional
    @Override
    public ProductImageDto getById(Long id) {
        log.info(String.format("getting product with id:%s", id));
        if (id < 0) throw new IllegalArgumentException("invalid product Image id");


        return null;
    }

    @Transactional
    @Override
    public ProductImageDto update(Long id, ProductImageDto productImageDto) {
        log.info(String.format("updating product image:%s", id));
        ProductImage productImage = productImageRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no product image found"));
        BeanUtils.copyProperties(productImageDto, productImage);
        ProductImage updatedProductImage = productImageRepository.save(productImage);
        log.info(String.format("updated product image with id:%s", id));
        BeanUtils.copyProperties(updatedProductImage, productImageDto);
        return productImageDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.info(String.format("deleting product image with id: %s", id));
        productImageRepository.deleteById(id);
        log.info(String.format("deleted product image with id: %s", id));


    }

    @Override
    public List<ProductImageDto> getAll() {
        return null;

    }


    public List<ProductResponseDto> getAllByProductId(Long productId) {
        log.info(String.format("getting all product images with productId:v%s", productId));

        return productImageRepository.findAllByProductId(productId);
    }

}
