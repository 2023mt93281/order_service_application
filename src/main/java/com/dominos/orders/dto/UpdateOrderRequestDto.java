package com.dominos.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequestDto {
	private int userId;
	private int orderId;
    private String shippingAddress;
    private LocalDateTime orderedDate;
    private BigDecimal totalAmount;
}
