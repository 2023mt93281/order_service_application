package com.dominos.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.dominos.orders.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
	private Integer userId;
    private String shippingAddress;
    private LocalDateTime orderedDate;
    private List<OrderItemDto> items;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod; 
}
