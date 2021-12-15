package com.aneeque.backendservice.department;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.aneeque.backendservice.util.Util.hasValue;

/**
 * @author Okala III
 */

@Getter
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        if (!hasValue(departmentDto.getName())) throw new IllegalArgumentException("invalid department name");
        Department savedDepartment = departmentRepository.save(new Department(departmentDto.getName()));
//        DepartmentDto departmentDto1 = new DepartmentDto();
        BeanUtils.copyProperties(savedDepartment,departmentDto);

        return departmentDto;
    }

    @Override
    public DepartmentResponseDto update(Long id,DepartmentDto departmentDto) {
        if (!hasValue(departmentDto.getName())) throw new IllegalArgumentException("invalid department name");
        Department savedDepartment = departmentRepository.findById(id).orElseThrow(()-> new NoSuchElementException("no department found"));
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(departmentDto,savedDepartment);

        Department updatedDepartment = departmentRepository.save(savedDepartment);

        BeanUtils.copyProperties(updatedDepartment, departmentResponseDto);

        return departmentResponseDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartmentList() {
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department->{
            DepartmentDto departmentDto = new DepartmentDto();
            BeanUtils.copyProperties(department,departmentDto);
            departmentDtoList.add(departmentDto);
        });
        return departmentDtoList;
    }
}
