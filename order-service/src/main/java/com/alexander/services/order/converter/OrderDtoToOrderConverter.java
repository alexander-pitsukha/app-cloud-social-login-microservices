package com.alexander.services.order.converter;

import com.alexander.services.library.dto.OrderDto;
import com.alexander.services.order.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {

    @Override
    public Order convert(OrderDto source) {
        Order order = new Order();
        order.setUserId(source.getUserId());
        order.setProductId(source.getProductId());
        order.setRazorpayPaymentId(source.getRazorpayPaymentId());
        order.setRazorpayOrderId(source.getRazorpayOrderId());
        order.setRazorpaySignature(source.getRazorpaySignature());
        return order;
    }

}
