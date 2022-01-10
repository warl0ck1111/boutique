package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.ProductSizeInformation;
import com.aneeque.backendservice.data.repository.ProductSizeInformationRepository;
import com.aneeque.backendservice.dto.request.ProductSizeInformationRequestDto;
import com.aneeque.backendservice.dto.response.ProductSizeInformationResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@Service
@Getter
public class ProductSizeInformationService {

    @Autowired
    private ProductSizeInformationRepository productSizeInformationRepository;

    public String create(ProductSizeInformationRequestDto dto) {
        productSizeInformationRepository.createProductSizeInformation(dto.getCategory(),dto.getName(), dto.getUnit(), dto.getFromX(), dto.getToY(), dto.getProductId());

        return "Product Size Information created successfully";
    }

    
    public String update(Long id, ProductSizeInformationRequestDto dto) {
        productSizeInformationRepository.updateProductSizeInformation(id, dto.getCategory(),dto.getName(), dto.getUnit(), dto.getFromX(), dto.getToY(), dto.getProductId());
        return "Product Size Information updated successfully";
    }
    
    public String delete(Long id) {
        productSizeInformationRepository.deleteProductSizeInformation(id);
        return "product size information deleted successfully";
    }

    public List<ProductSizeInformationResponseDto> getProductSizeInformationByProductId(Long productId) {
        List<ProductSizeInformation> productSizeInformations = productSizeInformationRepository.getProductSizeInformationByProductId(productId);
        List<ProductSizeInformationResponseDto> productSizeInformationResponseDtoList = new ArrayList<>();
        productSizeInformations.forEach(productSizeInformation -> {
            ProductSizeInformationResponseDto productSizeInformationResponseDto = new ProductSizeInformationResponseDto();
            BeanUtils.copyProperties(productSizeInformation, productSizeInformationResponseDto);
            productSizeInformationResponseDtoList.add(productSizeInformationResponseDto);
        });
        return productSizeInformationResponseDtoList;
    }
}
