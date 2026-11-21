package com.alexander.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "edition")
    private String edition;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

}
