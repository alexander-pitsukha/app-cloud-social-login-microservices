package com.alexander.service;

import com.alexander.dto.OrderDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService extends Service<OrderDto, Page<OrderDto>, Long> {

    List<OrderDto> findByProduct(Long productId);

    OrderDto save(String razorpayOrderId, Long userId);

    String validateAndUpdateOrder(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature, String secret);

}
