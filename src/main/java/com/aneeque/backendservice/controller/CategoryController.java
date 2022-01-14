package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.CategoryRequestDto;
import com.aneeque.backendservice.enums.CategoryType;
import com.aneeque.backendservice.service.CategoryService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    public static final String CREATE_DEPARTMENT_CATEGORY = "department";
    public static final String CREATE_PARENT_CATEGORY = "parent";
    public static final String CREATE_SUB_CATEGORY = "child";
    public static final String ADD_CHILD_CATEGORY_TO_A_PARENT = "{parentId}/add-child";
    public static final String GET_CATEGORY_BY_ID = "{categoryId}";
    public static final String UPDATE_CATEGORY = "{categoryId}";
    public static final String DELETE_CATEGORY = "{categoryId}";
    public static final String ASSIGN_ATTRIBUTES_TO_CATEGORY = "{categoryId}/assign-attributes";
    public static final String GET_ATTRIBUTES_ASSIGNED_TO_A_CATEGORY = "{categoryId}/get-attributes";

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRootParentsCategories() {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllParent()));
    }

    @PostMapping(CREATE_PARENT_CATEGORY)
    public ResponseEntity<ApiResponse> createParentCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.save(categoryRequestDto, CategoryType.PARENT_CATEGORY)));
    }

    @PostMapping(CREATE_DEPARTMENT_CATEGORY)
    public ResponseEntity<ApiResponse> createDepartmentRootCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.save(categoryRequestDto, CategoryType.DEPARTMENT)));
    }

    @PostMapping(CREATE_SUB_CATEGORY)
    public ResponseEntity<ApiResponse> createSubCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.save(categoryRequestDto, CategoryType.SUB_CATEGORY)));
    }

    @PostMapping(ADD_CHILD_CATEGORY_TO_A_PARENT)
    public ResponseEntity<ApiResponse> addChildCategoryToAParent(@PathVariable Long parentId, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.addChildToParent(parentId, categoryRequestDto)));
    }

    @GetMapping(GET_CATEGORY_BY_ID)
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getById(categoryId)));

    }


    @PutMapping(UPDATE_CATEGORY)
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(new ApiResponse("Category updated successfully", categoryService.update(categoryId, categoryRequestDto)));
    }

    @DeleteMapping(DELETE_CATEGORY)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully"));
    }

    @PutMapping(ASSIGN_ATTRIBUTES_TO_CATEGORY)
    public ResponseEntity<ApiResponse> assignAttributesToCategory(@PathVariable Long categoryId, @RequestParam Set<Long> attributes) {
        return ResponseEntity.ok(new ApiResponse("Attributes assigned to Category successfully", categoryService.assignAttributesToCategory(categoryId, attributes)));
    }

    @GetMapping(GET_ATTRIBUTES_ASSIGNED_TO_A_CATEGORY)
    public ResponseEntity<ApiResponse> getAllAttributesAssignedToCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllAssignedAttributesToCategory(categoryId)));
    }


}
