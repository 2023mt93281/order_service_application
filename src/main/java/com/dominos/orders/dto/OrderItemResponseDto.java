package com.dominos.orders.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
	private Integer orderItemId;

	private Integer productId;

	private Integer quantity;

	private BigDecimal unitPrice;
	
	private String itemName;
	
	private BigDecimal totalPrice;
}
