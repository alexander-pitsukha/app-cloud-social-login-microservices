package com.alexander.services.category.converter;

import com.alexander.dto.CategoryDto;
import com.alexander.services.category.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDto> {

    @Override
    public CategoryDto convert(Category source) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(source.getId());
        categoryDto.setTitle(source.getTitle());
        return categoryDto;
    }

}
