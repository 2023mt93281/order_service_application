package com.dominos.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dominos.orders.dto.PaymentRequestDto;
import com.dominos.orders.dto.PaymentResponseDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.Payment;
import com.dominos.orders.enums.PaymentMethod;
import com.dominos.orders.enums.PaymentStatusEnum;
import com.dominos.orders.repository.PaymentRepository;
import com.dominos.orders.utils.OrderUtils;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	@Lazy
	OrderService orderService;
	@Autowired
	OrderUtils orderutils;

	@Override
	public Payment addPaymentDetails(Order order, PaymentMethod paymentMethod) {
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setPaymentId(getPaymentId(order.getOrderId()));
		payment.setPaymentAmount(order.getTotalAmount());
		payment.setPaymentMethod(paymentMethod);
		payment.setPaymentStatus(PaymentStatusEnum.PENDING);
		Payment savedPayment = paymentRepository.save(payment);
		return savedPayment;
	}

	private String getPaymentId(Integer orderId) {
		return "PId" + orderId;
	}

	@Override
	public PaymentResponseDto makePayment(PaymentRequestDto paymentRequestDto) {
		Payment payment = paymentRepository.findByPaymentId(paymentRequestDto.getPaymentId());
		payment.setPaymentAmount(paymentRequestDto.getPaymentAmount());
		payment.setPaymentStatus(PaymentStatusEnum.COMPLETED);
		payment.setPaymentDate(paymentRequestDto.getPaymentDate());
		Payment savedPayment = paymentRepository.save(payment);
		PaymentResponseDto paymentResponse = orderutils.convertPaymentEntityToResponseDto(savedPayment);
		return paymentResponse;
	}

	@Override
	public String cancelPayment(String paymentId) {
		Payment payment = paymentRepository.findByPaymentId(paymentId);
		payment.setPaymentStatus(PaymentStatusEnum.FAILED);
		paymentRepository.save(payment);
		return "Payment cancelled succesfully";
	}

	@Override
	public PaymentResponseDto getPaymentByPaymentId(String paymentId) {
		Payment payment = paymentRepository.findByPaymentId(paymentId);
		PaymentResponseDto paymentResponseDto = orderutils.convertPaymentEntityToResponseDto(payment);
		return paymentResponseDto;
	}

	@Override
	public PaymentResponseDto getPaymentByOrderId(int orderId) {
		Order order = orderService.findOrder(orderId);
		Payment payment = paymentRepository.findByOrder(order);
		PaymentResponseDto paymentResponseDto = orderutils.convertPaymentEntityToResponseDto(payment);
		return paymentResponseDto;
	}

}
