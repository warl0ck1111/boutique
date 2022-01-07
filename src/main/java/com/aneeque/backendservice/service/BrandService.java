package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Brand;
import com.aneeque.backendservice.data.repository.BrandRepository;
import com.aneeque.backendservice.dto.request.BrandRequestDto;
import com.aneeque.backendservice.dto.response.BrandResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Service
@Getter
public class BrandService  {

    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public BrandResponseDto save(BrandRequestDto brandRequestDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandRequestDto,brand);
        Brand savedBrand = brandRepository.save(brand);
        BrandResponseDto brandResponseDto = new BrandResponseDto();
        BeanUtils.copyProperties(savedBrand, brandResponseDto);
        return brandResponseDto;
    }

    public BrandResponseDto getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no brand found"));
        BrandResponseDto brandResponseDto = new BrandResponseDto();
        BeanUtils.copyProperties(brand, brandResponseDto);

        return brandResponseDto;
    }

    @Transactional
    public String update(Long id, BrandRequestDto dto) {
        brandRepository.updateBrand(id, dto.getName());
        return "updated successfully";
    }

    @Transactional
    public String delete(Long id) {
        brandRepository.deleteByTagId(id);
        return "deleted successfully";
    }

    public List<BrandResponseDto> getAll() {
        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        List<Brand> brandList = brandRepository.findAll();
        brandList.forEach(brand -> {
            BrandResponseDto brandResponseDto = new BrandResponseDto();
            BeanUtils.copyProperties(brand, brandResponseDto);
            brandResponseDtoList.add(brandResponseDto);
        });
        return brandResponseDtoList;
    }
}
