package com.dominos.orders.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItemDto {
	private int orderItemId;
	private int productId;
    private int quantity;
    private BigDecimal unitPrice;
    private String itemName;
}
