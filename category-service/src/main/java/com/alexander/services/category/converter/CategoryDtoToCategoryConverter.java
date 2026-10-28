package com.alexander.services.category.converter;

import com.alexander.dto.CategoryDto;
import com.alexander.services.category.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto source) {
        Category category = new Category();
        category.setTitle(source.getTitle());
        return category;
    }

}
