package com.alexander.services.order.repo;

import com.alexander.services.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.productId = :productId")
    List<Order> findByProduct(@Param("productId") Long productId);

    @Query("select o from Order o where o.razorpayOrderId = :orderId")
    Optional<Order> findByRazorpayOrderId(@Param("orderId") String orderId);

}
