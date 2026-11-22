package com.alexander.services.category.service;

import com.alexander.services.library.dto.CategoryDto;
import com.alexander.services.library.service.Service;
import org.springframework.data.domain.Page;

public interface CategoryService extends Service<CategoryDto, Page<CategoryDto>, Long> {
}
