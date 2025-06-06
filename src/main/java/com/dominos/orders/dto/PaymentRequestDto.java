package com.dominos.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dominos.orders.enums.PaymentMethod;
import com.dominos.orders.enums.PaymentStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

	private int orderId;

	private String paymentId;

	private PaymentStatusEnum paymentStatus;

	private LocalDateTime paymentDate;

	private PaymentMethod paymentMethod;

	private BigDecimal paymentAmount;
}
