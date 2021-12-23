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
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSuperLevel() {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllParent()));
    }

    @PostMapping("create/parent")
    public ResponseEntity<ApiResponse> createASuperParent(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.save(categoryDto, CategoryType.PARENT_CATEGORY)));
    }

    @PostMapping("add-child/{parentId}")
    public ResponseEntity<ApiResponse> addChildToParent(@PathVariable Long parentId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse(categoryService.addChild(parentId, categoryDto)));
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getById(categoryId)));

    }


    @PutMapping("{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId,  @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse("Category updated successfully", categoryService.update(categoryId, categoryDto)));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully"));
    }

    @PutMapping("{categoryId}/assign-attr-prop")
    public ResponseEntity<ApiResponse> assignAttributesAndPropertiesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> attributes, @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Attributes and properties assigned to Category successfully", categoryService.assignAttributesAndPropertiesToCategory(categoryId, attributes, properties)));
    }

    @PutMapping("{categoryId}/assign-properties")
    public ResponseEntity<ApiResponse> assignPropertiesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> properties) {
        return ResponseEntity.ok(new ApiResponse("Properties assigned to Category successfully", categoryService.assignPropertiesToCategory(categoryId, properties)));
    }

    @PutMapping("{categoryId}/assign-attributes")
    public ResponseEntity<ApiResponse> assignAttributesToCategory(@PathVariable Long categoryId, @RequestParam List<Long> attributes) {
        return ResponseEntity.ok(new ApiResponse("Attributes assigned to Category successfully", categoryService.assignAttributesToCategory(categoryId, attributes)));
    }

    @GetMapping("{categoryId}/get-attributes")
    public ResponseEntity<ApiResponse> getAllAssignedAttributesToCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllAssignedAttributesToCategory(categoryId)));
    }

    @GetMapping("{categoryId}/get-properties")
    public ResponseEntity<ApiResponse> getAllAssignedPropertiesToCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAllAssignedPropertiesToCategory(categoryId)));
    }

}
