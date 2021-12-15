package com.aneeque.backendservice.category;

import com.aneeque.backendservice.response.ApiResponse;
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

    @GetMapping("{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new ApiResponse(categoryService.getById(categoryId)));

    }

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllCategories() {
        return ResponseEntity.ok(new ApiResponse(categoryService.getAll()));

    }

    @PostMapping()
    public ResponseEntity<ApiResponse> CreateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new ApiResponse("Category created successfully", categoryService.save(categoryDto)));
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
