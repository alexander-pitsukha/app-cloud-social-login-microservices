package com.alexander.client;

import com.alexander.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("orders/product/{productId}")
    ResponseEntity<List<OrderDto>> findByProduct(@PathVariable("productId") Long productId);

}
