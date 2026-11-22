package com.alexander.services.product.converter;

import com.alexander.services.library.dto.ProductDto;
import com.alexander.services.product.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    @Override
    public Product convert(ProductDto source) {
        Product product = new Product();
        product.setName(source.getName());
        product.setVersion(source.getVersion());
        product.setEdition(source.getEdition());
        product.setDescription(source.getDescription());
        product.setValidFrom(source.getValidFrom());
        product.setCategoryId(source.getCategoryId());
        return product;
    }

}
