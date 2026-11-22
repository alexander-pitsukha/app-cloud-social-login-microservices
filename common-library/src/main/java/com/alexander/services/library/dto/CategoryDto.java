package com.alexander.services.library.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto extends AbstractLongIdentifiableDto {

    @NotBlank
    private String title;

    private String description;

    private List<ProductDto> products = new ArrayList<>();

}
