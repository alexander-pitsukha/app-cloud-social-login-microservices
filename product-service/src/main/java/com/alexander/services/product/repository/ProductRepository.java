package com.alexander.services.product.repository;

import com.alexander.services.product.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("select p from Product p where p.categoryId = :categoryId")
    List<Product> findByCategory(@Param("categoryId") Long categoryId);

}
