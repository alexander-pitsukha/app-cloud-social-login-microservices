package com.alexander.services.category.controller;

import com.alexander.dto.CategoryDto;
import com.alexander.dto.ProductDto;
import com.alexander.services.category.client.ProductClient;
import com.alexander.services.category.service.CategoryService;
import com.alexander.util.ApiResponse;
import com.alexander.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductClient productClient;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok().body(categoryService.findAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping("{id}/products")
    public ResponseEntity<CategoryDto> getCategoryWithProducts(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        ResponseEntity<List<ProductDto>> response = productClient.findByCategory(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            categoryDto.setProducts(response.getBody());
        }
        return ResponseEntity.ok().body(categoryDto);
    }

    @GetMapping("{id}/products-with-orders")
    public ResponseEntity<CategoryDto> getCategoryWithProductsAndOrders(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        ResponseEntity<List<ProductDto>> response = productClient.findByCategoryWithOrders(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            categoryDto.setProducts(response.getBody());
        }
        return ResponseEntity.ok().body(categoryDto);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveCategory(@RequestBody @Validated CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody @Validated CategoryDto categoryDto) {
        categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

}
