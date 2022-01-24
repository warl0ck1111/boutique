package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.*;
import com.aneeque.backendservice.data.repository.ProductRepository;
import com.aneeque.backendservice.dto.request.*;
import com.aneeque.backendservice.dto.response.FindAllProductResponse;
import com.aneeque.backendservice.dto.response.FindAllProductResponseDto;
import com.aneeque.backendservice.dto.response.FindProductResponse;
import com.aneeque.backendservice.dto.response.ProductResponseDto;
import com.aneeque.backendservice.data.entity.ProductTag;
import com.aneeque.backendservice.data.repository.ProductTagRepository;
import com.aneeque.backendservice.service.impl.AttributeServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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
    private ProductMediaService productMediaService;

    @Autowired
    private ProductSizeInformationService productSizeInformationService;


    @Transactional
    public String createProduct(ProductCreateRequestDto dto) {
        log.info("creating product");
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        Product createdProduct = productRepository.save(product);
        long createdProductId = createdProduct.getId();

        if (!dto.getTags().isEmpty()) addProductTags(createdProductId, dto.getTags());

        if (!dto.getMediaFiles().isEmpty()) {
            productMediaService.addProductMedia(createdProductId, dto.getMediaFiles());
        }

        if(!dto.getCategories().isEmpty()) addProductCategories(createdProductId,dto.getCategories());
//        createProductSizeInformation(createdProductId, dto.getProductSizeInformation());
//        createProductProperties(createdProduct.getId(), dto.getProductProperties());

        return "product created successfully";
    }

    @Transactional
    public String updateProduct(Long productId, ProductUpdateRequestDto dto) {
        log.info("updating product");
        productRepository.updateProduct(productId, dto.getBrandName(), dto.getCostPrice(),
                dto.getDescription(), LocalDateTime.now().toString(), dto.getModifiedBy(),
                dto.getName(), dto.getPrice(), dto.getProductCode(), dto.getQuantity(), dto.getReorderPoint(),
                dto.getStockValue(), dto.getVendorId(), dto.getMaterialCareInfo(),
                dto.getPriceType(), dto.getSaleDuration(), dto.getSaleStatus(), dto.getSellingPrice(),
                dto.getTrackInventory());

        if (dto.updateMediaFiles) {
            productMediaService.removeAllProductImagesByProductId(productId);
            productMediaService.addProductMedia(productId, dto.getMediaFiles());
        }
        if (dto.updateTags) {
            removeProductTags(productId);
            addProductTags(productId, dto.getTags());
        }
        if (dto.updateCategories) {
            removeProductCategories(productId);
            addProductCategories(productId, dto.getCategories());
        }
        return "product updated successfully";
    }

    private void removeAllProductMedia(Long productId) {
        log.info("removing product media");
        productMediaService.removeAllProductImagesByProductId(productId);
    }

    private void deleteProductCategories(Long productId) {
        log.info("removing product Categories");
        productRepository.removeProductCategories(productId);
    }

    public ProductResponseDto findProductById(long productId) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        List<FindProductResponse> productResponses = productRepository.findProductById(productId);
        BeanUtils.copyProperties(productResponses.get(0), productResponseDto);
        productResponseDto.setId(productResponses.get(0).getProductId());
        Set<String> productTags = new HashSet<>();
        Set<ProductMediaDto> mediaFiles = new HashSet<>();
        Set<Long> categoryKeys = new HashSet<>();
        productResponses.stream().forEach(product -> {
            productTags.add(product.getTagName());
            mediaFiles.add(new ProductMediaDto(product.getFileName(), product.getFileType()));
            categoryKeys.add(product.getCategoryId());
        });
        productResponseDto.setTags(productTags);
        productResponseDto.setMediaFiles(mediaFiles);
        productResponseDto.setCategories(categoryKeys);

        return productResponseDto;
    }

    public Set<FindAllProductResponseDto> findAllProducts(int page, int size) {

        List<FindAllProductResponse> allProducts = productRepository.findAllProduct(PageRequest.of(page-1, size));
        Set<FindAllProductResponseDto> productDtoList = new HashSet<>();
        allProducts.forEach(product -> {
            log.info(String.format(""));
            FindAllProductResponseDto productDto = new FindAllProductResponseDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);
        });

        productDtoList.forEach(x->{
            allProducts.forEach(y->{
                if(Objects.equals(x.getProductId(), y.getProductId())){
                    x.getMediaFiles().add(new ProductMediaDto(y.getFileName(), y.getFileType()));
                    x.getTags().add(y.getTagName());
                    x.getCategoryNames().add(y.getCategoryName());
                }
            });
        });
        return productDtoList;
    }

        @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteProductById(productId);
        productMediaService.removeAllProductImagesByProductId(productId);
        productTagRepository.deleteProductTag(productId);
    }

    public List<ProductResponseDto> getAllProducts(int page, int size) {

        Page<Product> products = productRepository.findAll(PageRequest.of(page-1, size));
        List<ProductResponseDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductResponseDto productDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
    }

    public List<ProductResponseDto> searchProducts(String productName, int page, int size) {

        Page<Product> products = productRepository.findByNameContainingIgnoreCase(productName, PageRequest.of(page -1, size));
        List<ProductResponseDto> productDtoList = new ArrayList<>();

        products.forEach(product -> {
            ProductResponseDto productDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productDto);
            productDtoList.add(productDto);

        });

        return productDtoList;
    }


    @Transactional
    public ProductCreateRequestDto assignAttributesToProduct(Long productId, List<Long> attributeIds) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Attribute> attributes = attributeService.getAttributeRepository().findAllById(attributeIds);

        Product updatedProduct = productRepository.save(product);
        ProductCreateRequestDto productCreateRequestDto = new ProductCreateRequestDto();
        BeanUtils.copyProperties(updatedProduct, productCreateRequestDto);
        return productCreateRequestDto;

    }

    @Transactional
    public ProductCreateRequestDto assignPropertiesToProduct(Long productId, List<Long> propertyId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        List<Property> properties = propertyService.getPropertyRepository().findAllById(propertyId);
//        product.setProperties(properties);

        Product updatedProduct = productRepository.save(product);
        ProductCreateRequestDto productCreateRequestDto = new ProductCreateRequestDto();
        BeanUtils.copyProperties(updatedProduct, productCreateRequestDto);
        return productCreateRequestDto;


    }

    @Transactional
    void createProductProperties(long productId, List<ProductPropertiesRequestDto> productProperties) {
        log.info("creating product property");
        productProperties.forEach(dto -> productRepository.addProductProperty(productId, dto.getPropertyId(), dto.getPrice()));
    }

    @Transactional
    public String deleteProductProperty(Long productId, Long propertyId) {
        log.info("deleting product property");
        productRepository.removeProductProperty(productId, propertyId);
        return "product property deleted successfully";
    }

    @Transactional
    void createProductSizeInformation(Long productId, List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        log.info("creating product size information");
        productSizeInformationRequestDtos.forEach(prodSizeInfo -> {
            productSizeInformationService.create(prodSizeInfo);
        });
    }

    @Transactional
    void updateProductSizeInformation(long productId, List<ProductSizeInformationRequestDto> productSizeInformationRequestDtos) {
        productSizeInformationRequestDtos.forEach(productSizeInformation -> {
            productSizeInformationService.update(productId, productSizeInformation);
        });
    }

    @Transactional
    void addProductCategories(long productId, Set<Long> categories) {
        log.info("adding product categories");
        categories.forEach(categoryId->productRepository.addProductCategory(productId, categoryId));
    }

    @Transactional
    void removeProductCategories(Long productId){
        productRepository.removeProductCategories(productId);
    }

    @Transactional
    void addProductTags(Long productId, Set<String> tags) {
        log.info("creating ProductTags");
        tags.forEach(tag -> {
            ProductTag productTag = new ProductTag(productId, tag);
            productTagRepository.save(productTag);
        });
    }

    @Transactional
    void removeProductTags(Long productId) {
        log.info("deleting product Tags");
        productTagRepository.deleteProductTag(productId);
    }


}
