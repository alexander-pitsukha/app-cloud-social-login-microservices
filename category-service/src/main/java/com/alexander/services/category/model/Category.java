package com.alexander.services.category.model;

import com.alexander.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

}
