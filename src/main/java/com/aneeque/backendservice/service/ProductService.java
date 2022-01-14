package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.*;
import com.aneeque.backendservice.data.repository.ProductRepository;
import com.aneeque.backendservice.dto.request.ProductCreateRequestDto;
import com.aneeque.backendservice.dto.request.ProductPropertiesRequestDto;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.data.entity.ProductTag;
import com.aneeque.backendservice.data.repository.ProductTagRepository;
import com.aneeque.backendservice.dto.request.ProductSizeInformationRequestDto;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.util.IAuthenticationFacade;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */
@Slf4j
@Getter
@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AttributeServiceImpl attributeService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ProductTagRepository productTagRepository;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private ProductSizeInformationService productSizeInformationService;


    @Transactional
    public String createProduct(ProductCreateRequestDto dto) {
        log.info("creating product");
        Integer createdProductId = productRepository.createProduct(dto.getBrandName(), dto.getCategoryId(),
                LocalDateTime.now().toString(), dto.getCreatedBy(), dto.getDescription(), dto.getName(),
                dto.getPrice(), dto.getProductCode(), dto.getVendorId(), dto.getCostPrice(), dto.getQuantity(),
                dto.getReorderPoint(), dto.getStockValue(), null, null);

        System.out.println("this is the created product Id: " + createdProductId);
        createProductTags(Long.valueOf(createdProductId), dto);
        createProductSizeInformation(dto.getProductSizeInformation());
        createProductProperties(createdProductId, dto.getProductProperties());

        return "product created successfully";
    }

    private void createProductProperties(int productId, List<ProductPropertiesRequestDto> productProperties) {
        log.info("creating product property");
        productProperties.forEach(dto -> {
            productRepository.addProductProperty((long) productId, dto.getPropertyId(), dto.getPrice());
        });
    }

    private void createProductSizeInformation(List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        log.info("creating product size information");
        productSizeInformationRequestDtos.forEach(prodSizeInfo -> {
            productSizeInformationService.create(prodSizeInfo);
        });
    }

    private void createProductTags(Long productId, ProductCreateRequestDto dto) {
        log.info("creating ProductTags");
        dto.getTagIds().forEach(tagId -> {
            ProductTag productTag = new ProductTag(productId, tagId);
            productTagRepository.save(productTag);
        });
    }


    public String deleteProductTag(Long productId, Long tagId) {
        log.info("deleting product Tag");
        productTagRepository.deleteProductTag(productId, tagId);
        return "product tag removed successfully";
    }

    public String deleteProductProperty(Long productId, Long propertyId) {
        log.info("deleting product property");
        productRepository.removeProductProperty(productId, propertyId);
        return "product property deleted successfully";
    }


    public ProductCreateRequestDto getProductById(Long id) {
        log.info("getting ProductById");
        if (id < 0) throw new IllegalArgumentException("invalid product id");
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("product not found"));
        ProductCreateRequestDto productCreateRequestDto = new ProductCreateRequestDto();

        BeanUtils.copyProperties(product, productCreateRequestDto);
        return productCreateRequestDto;
    }

    public String updateProduct(Long productId, ProductCreateRequestDto dto) {
        log.info("updating product");
        productRepository.updateProduct(productId, dto.getBrandName(), dto.getCategoryId(),
                LocalDateTime.now().toString(), dto.getCreatedBy(), dto.getDescription(), dto.getName(),
                dto.getPrice(), dto.getProductCode(), dto.getVendorId(), dto.getCostPrice(), dto.getQuantity(),
                dto.getReorderPoint(), dto.getStockValue(), dto.getModifiedBy(), LocalDateTime.now().toString());

        updateProductSizeInformation(productId, dto.getProductSizeInformation());

        return "product updated successfully";
    }

    private void updateProductSizeInformation(long productId, List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        productSizeInformationRequestDtos.forEach(productSizeInformation -> {
            productSizeInformationService.update(productId, productSizeInformation);
        });
    }

    public void delete(Long productId) {
        productRepository.deleteProductById(productId);
    }


    public List<ProductResponseDto> getAllProducts(int page, int size) {

        List<Product> products = productRepository.findAllProducts(PageRequest.of(page, size));
        List<ProductResponseDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductResponseDto productDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
    }


    public List<ProductCreateRequestDto> getAllByProperties(Long propertyId) {
//
//        List<Product> products = productRepository.findAllByPropertiesIn(Arrays.asList(propertyId));
//        List<ProductCreateRequestDto> productRequestDtoList = new ArrayList<>();
//
//        products.forEach(product -> {
//            ProductCreateRequestDto productRequestDto = new ProductCreateRequestDto();
//            BeanUtils.copyProperties(product, productRequestDto);
//            productRequestDtoList.add(productRequestDto);
//
//        });

//        return productRequestDtoList;
        return null;
    }


    public ProductCreateRequestDto assignAttributesToProduct(Long productId, List<Long> attributeIds) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);

        Product updatedProduct = productRepository.save(product);
        ProductCreateRequestDto productCreateRequestDto = new ProductCreateRequestDto();
        BeanUtils.copyProperties(updatedProduct, productCreateRequestDto);
        return productCreateRequestDto;

    }

    public ProductCreateRequestDto assignPropertiesToProduct(Long productId, List<Long> propertyId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyId);
//        product.setProperties(properties);

        Product updatedProduct = productRepository.save(product);
        ProductCreateRequestDto productCreateRequestDto = new ProductCreateRequestDto();
        BeanUtils.copyProperties(updatedProduct, productCreateRequestDto);
        return productCreateRequestDto;


    }

}
