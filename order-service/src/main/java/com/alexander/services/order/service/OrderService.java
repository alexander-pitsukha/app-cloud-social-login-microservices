package com.alexander.services.order.service;

import com.alexander.services.library.dto.OrderDto;
import com.alexander.services.library.service.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService extends Service<OrderDto, Page<OrderDto>, Long> {

    List<OrderDto> findByProduct(Long productId);

    OrderDto save(String razorpayOrderId, Long userId, Long productId);

    String validateAndUpdateOrder(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature, String secret);

}
