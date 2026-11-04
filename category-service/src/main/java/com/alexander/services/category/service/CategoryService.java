package com.alexander.services.category.service;

import com.alexander.dto.CategoryDto;
import com.alexander.service.Service;
import org.springframework.data.domain.Page;

public interface CategoryService extends Service<CategoryDto, Page<CategoryDto>, Long> {
}
