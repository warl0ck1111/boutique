package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.request.CategoryDto;
import com.aneeque.backendservice.enums.CategoryType;
import com.aneeque.backendservice.service.CategoryService;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    public static final String CREATE_PARENT_ROOT_CATEGORY = "create/parent";
    public static final String ADD_CHILD_CATEGORY_TO_A_PARENT = "add-child/{parentId}";
    public static final String GET_CATEGORY_BY_ID = "{categoryId}";
    public static final String UPDATE_CATEGORY = "{categoryId}";
    public static final String DELETE_CATEGORY = "{categoryId}";
    public static final String ASSIGN_ATTRIBUTES_AND_PROPERTIES_TO_CATEGORY = "{categoryId}/assign/attr-prop";
    public static final String ASSIGN_PROPERTIES_TO_CATEGORY = "{categoryId}/assign/properties";
    public static final String ASSIGN_ATTRIBUTES_TO_CATEGORY = "{categoryId}/assign-attributes";
    public static final String GET_ATTRIBUTES_ASSIGNED_TO_A_CATEGORY = "{categoryId}/get-attributes";
    public static final String GET_ALL_PROPERTIES_ASSIGNED_TO_A_CATEGORY = "{categoryId}/get-properties";

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRootParents() {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllParent()));
    }

    @PostMapping(CREATE_PARENT_ROOT_CATEGORY)
    public ResponseEntity<ApiResponse> createARootParentCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.save(categoryDto, CategoryType.PARENT_CATEGORY)));
    }

    @PostMapping(ADD_CHILD_CATEGORY_TO_A_PARENT)
    public ResponseEntity<ApiResponse> addChildCategoryToAParent(@PathVariable Long parentId, @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.addChild(parentId, categoryDto)));
    }

    @GetMapping(GET_CATEGORY_BY_ID)
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getById(categoryId)));

    }


    @PutMapping(UPDATE_CATEGORY)
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId,  @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse("Category updated successfully", categoryService.update(categoryId, categoryDto)));
    }

    @DeleteMapping(DELETE_CATEGORY)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully"));
    }

    @PutMapping(ASSIGN_ATTRIBUTES_AND_PROPERTIES_TO_CATEGORY)
    public ResponseEntity<ApiResponse> assignAttributesAndPropertiesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> attributes, @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Attributes and properties assigned to Category successfully", categoryService.assignAttributesAndPropertiesToCategory(categoryId, attributes, properties)));
    }

    @PutMapping(ASSIGN_PROPERTIES_TO_CATEGORY)
    public ResponseEntity<ApiResponse> assignPropertiesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Properties assigned to Category successfully", categoryService.assignPropertiesToCategory(categoryId, properties)));
    }

    @PutMapping(ASSIGN_ATTRIBUTES_TO_CATEGORY)
    public ResponseEntity<ApiResponse> assignAttributesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> attributes) {
        return ResponseEntity.ok(new ApiResponse("Attributes assigned to Category successfully", categoryService.assignAttributesToCategory(categoryId, attributes)));
    }

    @GetMapping(GET_ATTRIBUTES_ASSIGNED_TO_A_CATEGORY)
    public ResponseEntity<ApiResponse> getAllAttributesAssignedToCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllAssignedAttributesToCategory(categoryId)));
    }

    @GetMapping(GET_ALL_PROPERTIES_ASSIGNED_TO_A_CATEGORY)
    public ResponseEntity<ApiResponse> getAllPropertiesAssignedToCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllAssignedPropertiesToCategory(categoryId)));
    }

}
