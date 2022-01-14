package com.aneeque.backendservice.service;

import com.aneeque.backendservice.dto.request.AttributeRequestDto;
import com.aneeque.backendservice.dto.response.AttributeResponseDto;

import java.util.List;

/**
 * @author Okala III
 */

public interface AttributeService {
    AttributeResponseDto save(AttributeRequestDto attributeRequestDto);

    AttributeResponseDto update(Long id, AttributeRequestDto attributeRequestDto);

    List<AttributeResponseDto> getAllAttributes();



}
