package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.*;
import com.aneeque.backendservice.data.repository.ProductRepository;
import com.aneeque.backendservice.dto.request.ProductDto;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.util.CrudService;
import com.aneeque.backendservice.util.IAuthenticationFacade;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.aneeque.backendservice.util.Util.hasValue;

/**
 * @author Okala Bashir .O.
 */

@Getter
@Service
public class ProductService implements CrudService<Product, ProductDto> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AttributeServiceImpl attributeService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @Override
    public ProductDto save(ProductDto productDto) {
        if (!hasValue(productDto.getName())) throw new IllegalArgumentException("product name can not be empty");
        if (!hasValue(productDto.getDescription()))
            throw new IllegalArgumentException("product description can not be empty");

        Category category = categoryService.getCategoryRepository().findById(productDto.getCategory()).orElseThrow(() -> new NoSuchElementException("category not found"));
        Authentication authentication = authenticationFacade.getAuthentication();
        AppUser loggedInUser = (AppUser) authentication.getPrincipal();

        Product product = new Product(productDto.getName(), productDto.getDescription(), category, productDto.getPrice(), loggedInUser);
        Product savedProduct = productRepository.save(product);

        BeanUtils.copyProperties(savedProduct, productDto);

        return productDto;
    }

    @Override
    public ProductDto getById(Long id) {
        if (id < 0) throw new IllegalArgumentException("invalid product id");
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("product not found"));
        ProductDto productDto = new ProductDto();

        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        if (!hasValue(productDto.getName())) throw new IllegalArgumentException("product name can not be empty");
        if (!hasValue(productDto.getDescription()))
            throw new IllegalArgumentException("product description can not be empty");

        Category category = categoryService.getCategoryRepository().findById(productDto.getCategory()).orElseThrow(() -> new NoSuchElementException("category not found"));
        Authentication authentication = authenticationFacade.getAuthentication();
        AppUser loggedInUser = (AppUser) authentication.getPrincipal();

        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("product not found"));
        BeanUtils.copyProperties(productDto, product);
        product.setCategory(category);
        product.setModifiedBy(loggedInUser);

        Product updatedProduct = productRepository.save(product);

        BeanUtils.copyProperties(updatedProduct, productDto);
        return productDto;
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public List<ProductDto> getAll() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
    }


    public List<ProductDto> getAllByProperties(Long propertyId) {

        List<Product> products = productRepository.findAllByPropertiesIn(Arrays.asList(propertyId));
        List<ProductDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
    }




    public ProductDto assignAttributesToProduct(Long productId, List<Long> attributeIds) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);
        product.setAttributes(attributes);

        Product updatedProduct = productRepository.save(product);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(updatedProduct, productDto);
        return productDto;

    }

    public ProductDto assignPropertiesToProduct(Long productId, List<Long> propertyId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyId);
        product.setProperties(properties);

        Product updatedProduct = productRepository.save(product);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(updatedProduct, productDto);
        return productDto;


    }

}
