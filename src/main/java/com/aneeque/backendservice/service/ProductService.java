package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.*;
import com.aneeque.backendservice.data.repository.ProductRepository;
import com.aneeque.backendservice.dto.request.ProductCreateRequestDto;
import com.aneeque.backendservice.dto.request.ProductImageDto;
import com.aneeque.backendservice.dto.request.ProductPropertiesRequestDto;
import com.aneeque.backendservice.dto.response.FindProductResponse;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.data.entity.ProductTag;
import com.aneeque.backendservice.data.repository.ProductTagRepository;
import com.aneeque.backendservice.dto.request.ProductSizeInformationRequestDto;
import com.aneeque.backendservice.dto.response.TagResponseDto;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import com.aneeque.backendservice.util.IAuthenticationFacade;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        Product createdProduct = productRepository.save(product);
        long createdProductId = createdProduct.getId();

        System.out.println("this is the created product Id: " + createdProduct.getId());
        createProductTags(createdProductId, dto);

        ProductImageDto productImageDto = new ProductImageDto(dto.getProductImage(), createdProductId);
        productImageService.save(productImageDto);
//        createProductSizeInformation(createdProductId, dto.getProductSizeInformation());
        createProductProperties(createdProduct.getId(), dto.getProductProperties());

        return "product created successfully";
    }

    @Transactional
    public String updateProduct(Long productId, ProductCreateRequestDto dto) {
        log.info("updating product");
        productRepository.updateProduct(productId, dto.getBrandName(), dto.getCategoryId(),  dto.getCostPrice(),
                dto.getDescription(), LocalDateTime.now().toString(), dto.getModifiedBy(),
                dto.getName(), dto.getPrice(), dto.getProductCode(), dto.getQuantity(), dto.getReorderPoint(),
                dto.getStockValue(), dto.getVendorId(), dto.getMaterialCareInfo(), dto.getPreferredVendor(),
                dto.getPriceType(), dto.getSaleDuration(), dto.getSaleStatus(), dto.getSellingPrice(),
                dto.getTrackInventory(), dto.getUnit());

//        updateProductSizeInformation(productId, dto.getProductSizeInformation());

        return "product updated successfully";
    }

    public ProductResponseDto findProductById(long productId) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        List<FindProductResponse> productResponses = productRepository.findProductById(productId);
        BeanUtils.copyProperties(productResponses.get(0), productResponseDto);
        productResponseDto.setId(productResponses.get(0).getProductId());
        List<String> productTags = new ArrayList<>();
        productResponses.stream().forEach(product -> {
            productTags.add(product.getTagName());
        });
        productResponseDto.setTags(productTags);

        return productResponseDto;
    }

    private void createProductProperties(long productId, List<ProductPropertiesRequestDto> productProperties) {
        log.info("creating product property");
        productProperties.forEach(dto -> {
            productRepository.addProductProperty(productId, dto.getPropertyId(), dto.getPrice());
        });
    }

    private void createProductSizeInformation(Long productId, List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        log.info("creating product size information");
        productSizeInformationRequestDtos.forEach(prodSizeInfo -> {
            productSizeInformationService.create(prodSizeInfo);
        });
    }

    private void createProductTags(Long productId, ProductCreateRequestDto dto) {
        log.info("creating ProductTags");
        dto.getTags().forEach(tag -> {
            ProductTag productTag = new ProductTag(productId, tag);
            productTagRepository.save(productTag);
        });
    }


    @Transactional
    public String deleteProductTag(Long productId, String tagName) {
        log.info("deleting product Tag");
        productTagRepository.deleteProductTag(productId, tagName);
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

    private void updateProductSizeInformation(long productId, List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        productSizeInformationRequestDtos.forEach(productSizeInformation -> {
            productSizeInformationService.update(productId, productSizeInformation);
        });
    }

    public void delete(Long productId) {
        productRepository.deleteProductById(productId);
    }


    public List<ProductResponseDto> getAllProducts(int page, int size) {

        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        List<ProductResponseDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductResponseDto productDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
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
