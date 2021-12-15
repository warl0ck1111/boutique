package com.aneeque.backendservice.department;

import com.aneeque.backendservice.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new ApiResponse("Department created successfully",departmentService.save(departmentDto)));
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<ApiResponse> updateDepartment(@PathVariable String departmentId, @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(new ApiResponse("Department updated successfully",departmentService.update( Long.valueOf(departmentId), departmentDto)));
    }


}
