package com.alexander.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDto extends AbstractLongIdentifiableDto {

    @NonNull
    private Long userId;

    private String razorpayPaymentId;

    private String razorpayOrderId;

    private String razorpaySignature;

    @NotNull
    private Long productId;

}
