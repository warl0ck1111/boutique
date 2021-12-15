package com.aneeque.backendservice.department;

import java.util.List;

/**
 * @author Okala III
 */

public interface DepartmentService {


    DepartmentDto save(DepartmentDto departmentDto);

    DepartmentResponseDto update(Long id,DepartmentDto departmentDto);

    List<?> getAllDepartmentList();
}
