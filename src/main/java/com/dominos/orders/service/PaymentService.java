package com.dominos.orders.service;

import org.springframework.stereotype.Service;

import com.dominos.orders.dto.PaymentRequestDto;
import com.dominos.orders.dto.PaymentResponseDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.Payment;
import com.dominos.orders.enums.PaymentMethod;

@Service
public interface PaymentService {
	Payment addPaymentDetails(Order order, PaymentMethod paymentMethod);
	PaymentResponseDto makePayment(PaymentRequestDto paymentRequestDto);
	String cancelPayment(String paymentId);
	PaymentResponseDto getPaymentByPaymentId(String paymentId);
	PaymentResponseDto getPaymentByOrderId(int orderId);
}
