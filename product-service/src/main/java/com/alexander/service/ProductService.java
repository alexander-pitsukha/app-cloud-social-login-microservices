package com.alexander.service;

import com.alexander.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends Service<ProductDto, Page<ProductDto>, Long> {

    List<ProductDto> findByCategory(Long categoryId);

}
