package com.alexander.category.client;

import com.alexander.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("products/category/{categoryId}")
    ResponseEntity<List<ProductDto>> getByCategory(@PathVariable Long categoryId);

    @GetMapping("products/category/{categoryId}/with-orders")
    ResponseEntity<List<ProductDto>> getByCategoryWithOrders(@PathVariable Long categoryId);

}
