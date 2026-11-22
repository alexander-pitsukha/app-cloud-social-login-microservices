package com.alexander.services.product.service;

import com.alexander.services.library.dto.ProductDto;
import com.alexander.services.library.service.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends Service<ProductDto, Page<ProductDto>, Long> {

    List<ProductDto> findByCategory(Long categoryId);

}
