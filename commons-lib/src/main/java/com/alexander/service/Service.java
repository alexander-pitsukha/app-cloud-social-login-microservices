package com.alexander.service;

import com.alexander.dto.AbstractLongIdentifiableDto;

import java.io.Serializable;

public interface Service<T extends AbstractLongIdentifiableDto, P, I extends Serializable> {

    P findAll(int page, int size);

    T findById(I id);

    T save(T t);

    void update(I id, T t);

    void deleteById(I id);

}
