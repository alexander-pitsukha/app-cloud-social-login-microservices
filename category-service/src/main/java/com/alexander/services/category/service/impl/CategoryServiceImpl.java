package com.alexander.services.category.service.impl;

import com.alexander.dto.CategoryDto;
import com.alexander.exception.ResourceNotFoundException;
import com.alexander.services.category.model.Category;
import com.alexander.services.category.repository.CategoryRepository;
import com.alexander.services.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.alexander.util.AppConstants.ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DefaultConversionService defaultConversionService;

    @Override
    public Page<CategoryDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> defaultConversionService.convert(category, CategoryDto.class));
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", ID, id));
        return defaultConversionService.convert(category, CategoryDto.class);
    }

    @Transactional
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = Objects.requireNonNull(defaultConversionService.convert(categoryDto, Category.class));
        return defaultConversionService.convert(categoryRepository.save(category), CategoryDto.class);
    }

    @Transactional
    @Override
    public void update(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", ID, id));
        category.setTitle(category.getTitle());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
