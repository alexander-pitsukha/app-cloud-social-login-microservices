package com.alexander.services.library.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDto extends AbstractLongIdentifiableDto {

    @NotBlank
    private String name;

    @NotBlank
    private String version;

    private String edition;

    @NotBlank
    private String description;

    private LocalDate validFrom;

    @NotNull
    private Long categoryId;

    private List<OrderDto> orders = new ArrayList<>();

}
