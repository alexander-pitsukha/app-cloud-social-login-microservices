package com.alexander.services.order.converter;

import com.alexander.services.library.dto.OrderDto;
import com.alexander.services.order.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {

    @Override
    public OrderDto convert(Order source) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(source.getId());
        orderDto.setUserId(source.getUserId());
        orderDto.setProductId(source.getProductId());
        orderDto.setRazorpayPaymentId(source.getRazorpayPaymentId());
        orderDto.setRazorpayOrderId(source.getRazorpayOrderId());
        orderDto.setRazorpaySignature(source.getRazorpaySignature());
        return orderDto;
    }

}
