package com.alexander.repo;

import com.alexander.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.productId = :productId")
    List<Order> findByProduct(@Param("productId") Long productId);

    @Query("select o from Order o where o.razorpayOrderId = :orderId")
    Order findByRazorpayOrderId(@Param("orderId") String orderId);

}
