package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.ProductMedia;
import com.aneeque.backendservice.data.repository.ProductMediaRepository;
import com.aneeque.backendservice.dto.request.ProductMediaDto;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.util.CrudService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@Slf4j
@Getter
@Service
public class ProductMediaService implements CrudService<ProductMedia, ProductMediaDto> {

    @Autowired
    private ProductMediaRepository productMediaRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    @Override
    public ProductMediaDto save(ProductMediaDto dto) {
        log.info("creating product image");
        ProductMedia productMedia = new ProductMedia();
        BeanUtils.copyProperties(dto, productMedia);

        ProductMedia savedProductMedia = productMediaRepository.save(productMedia);
        BeanUtils.copyProperties(savedProductMedia, dto);
        return dto;

    }

    @Transactional
    public String addProductMedia(Long productId, Set<ProductMediaDto> fileNames) {
        log.info("adding product media");
        fileNames.forEach(media->{
            productMediaRepository.addMediaFile(LocalDateTime.now().toString(), media.getFileName(), media.getFileType(), null, productId);
        });
        return "product media files added";

    }
 @Transactional
    public String removeAllProductImagesByProductId(Long productId) {
        log.info("removing product media");
        productMediaRepository.removeAllProductImagesByProductId(productId);
        return "product media files removed";

    }

    @Transactional
    @Override
    public ProductMediaDto getById(Long id) {
        log.info(String.format("getting product with id:%s", id));
        if (id < 0) throw new IllegalArgumentException("invalid product Image id");


        return null;
    }

    @Transactional
    @Override
    public ProductMediaDto update(Long id, ProductMediaDto productMediaDto) {
        log.info(String.format("updating product image:%s", id));
        ProductMedia productMedia = productMediaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no product image found"));
        BeanUtils.copyProperties(productMediaDto, productMedia);
        ProductMedia updatedProductMedia = productMediaRepository.save(productMedia);
        log.info(String.format("updated product image with id:%s", id));
        BeanUtils.copyProperties(updatedProductMedia, productMediaDto);
        return productMediaDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.info(String.format("deleting product image with id: %s", id));
        productMediaRepository.deleteById(id);
        log.info(String.format("deleted product image with id: %s", id));


    }

    @Override
    public List<ProductMediaDto> getAll() {
        return null;

    }


    public List<ProductResponseDto> getAllByProductId(Long productId) {
        log.info(String.format("getting all product images with productId:v%s", productId));

        return productMediaRepository.findAllByProductId(productId);
    }

}
