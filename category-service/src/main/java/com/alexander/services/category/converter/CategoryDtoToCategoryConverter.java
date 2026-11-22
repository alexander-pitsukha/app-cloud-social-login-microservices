package com.alexander.services.category.converter;

import com.alexander.services.category.entity.Category;
import com.alexander.services.library.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto source) {
        Category category = new Category();
        category.setTitle(source.getTitle());
        category.setDescription(source.getDescription());
        return category;
    }

}
