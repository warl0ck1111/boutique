package com.aneeque.backendservice.service;

import com.aneeque.backendservice.dto.request.DepartmentDto;
import com.aneeque.backendservice.dto.response.DepartmentResponseDto;

import java.util.List;

/**
 * @author Okala III
 */

public interface DepartmentService {


    DepartmentDto save(DepartmentDto departmentDto);

    DepartmentResponseDto update(Long id, DepartmentDto departmentDto);

    List<?> getAllDepartmentList();
}
