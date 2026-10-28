package com.alexander.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto extends AbstractLongIdentifiableDto {

    @NotNull
    @NotBlank
    private String title;

    private List<ProductDto> products = new ArrayList<>();

}
