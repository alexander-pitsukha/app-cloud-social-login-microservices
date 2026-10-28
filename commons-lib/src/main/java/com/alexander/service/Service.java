package com.alexander.service;

public interface Service<T, P, I> {

    P findAll(int page, int size);

    T findById(I id);

    T save(T t);

    void update(I id, T t);

    void deleteById(I id);

}
