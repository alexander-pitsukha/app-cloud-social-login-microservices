package com.alexander.dto;

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

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String version;

    private String edition;

    @NotNull
    @NotBlank
    private String description;

    private LocalDate validFrom;

    @NotNull
    private Long categoryId;

    private List<OrderDto> orders = new ArrayList<>();

}
