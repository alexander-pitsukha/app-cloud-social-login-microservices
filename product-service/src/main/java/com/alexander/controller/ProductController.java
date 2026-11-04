package com.alexander.controller;

import com.alexander.client.OrderClient;
import com.alexander.dto.OrderDto;
import com.alexander.dto.ProductDto;
import com.alexander.service.ProductService;
import com.alexander.util.ApiResponse;
import com.alexander.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final OrderClient orderClient;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok().body(productService.findAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<ProductDto>> findByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok().body(productService.findByCategory(categoryId));
    }

    @GetMapping("category/{categoryId}/with-orders")
    public ResponseEntity<List<ProductDto>> findByCategoryWithOrders(@PathVariable("categoryId") Long categoryId) {
        List<ProductDto> productDtos = productService.findByCategory(categoryId);
        productDtos.forEach(productDto -> {
            ResponseEntity<List<OrderDto>> response = orderClient.findByProduct(productDto.getId());
            if (response.getStatusCode().is2xxSuccessful()) {
                productDto.setOrders(response.getBody());
            }
        });
        return ResponseEntity.ok().body(productDtos);
    }

    @GetMapping("{id}/with-orders")
    public ResponseEntity<ProductDto> getProductWithOrders(@PathVariable Long id) {
        ProductDto productDto = productService.findById(id);
        ResponseEntity<List<OrderDto>> response = orderClient.findByProduct(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            productDto.setOrders(response.getBody());
        }
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveProduct(@RequestBody @Validated ProductDto productDto) {
        productService.save(productDto);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody @Validated ProductDto productDto) {
        productService.update(id, productDto);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
    }

}
