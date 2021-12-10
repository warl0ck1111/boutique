package com.aneeque.backendservice.category.department;

import com.aneeque.backendservice.response.ApiResponse;
import com.aneeque.backendservice.role.Role;
import com.aneeque.backendservice.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Okala III
 */

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getDepartments() {
        return ResponseEntity.ok(new ApiResponse(departmentService.getAllDepartmentList()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(new ApiResponse(departmentService.save(departmentDto)));
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<ApiResponse> updateDepartment(@PathVariable String departmentId, @RequestBody DepartmentDto departmentDto) {
        System.out.println("the department Id is "+departmentId);
        return ResponseEntity.ok(new ApiResponse(departmentService.update( Long.valueOf(departmentId), departmentDto)));
    }


}
