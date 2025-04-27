package com.dominos.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.dominos.orders.enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrderResponseDto {
	private Integer orderId;

	private Integer userId;

	private LocalDateTime orderDate;

	private BigDecimal totalAmount;

	private String shippingAddress;

	private OrderStatusEnum orderStatus;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private List<OrderItemResponseDto> orderItems;

	private PaymentResponseDto paymentDetails;
}
