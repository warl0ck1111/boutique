package com.aneeque.backendservice.category.department;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Okala III
 */

public interface DepartmentService {


    DepartmentDto save(DepartmentDto departmentDto);

    DepartmentResponseDto update(Long id,DepartmentDto departmentDto);

    List<?> getAllDepartmentList();
}
