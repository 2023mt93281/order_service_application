package com.dominos.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dominos.orders.dto.PaymentRequestDto;
import com.dominos.orders.dto.PaymentResponseDto;
import com.dominos.orders.service.PaymentService;

@RestController
@RequestMapping("/api/orderpayment")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@PutMapping("/makepayment")
	public ResponseEntity<PaymentResponseDto> makePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
		PaymentResponseDto paymentResponseDto = paymentService.makePayment(paymentRequestDto);
		return new ResponseEntity<PaymentResponseDto>(paymentResponseDto, HttpStatus.OK);
	}
	@PutMapping("/cancel")
	public ResponseEntity<String> cancelPayment(@RequestParam String paymentId) {
		String response = paymentService.cancelPayment(paymentId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	@GetMapping("/getbyPaymentId")
	public ResponseEntity<PaymentResponseDto> getPaymentByPaymentId(@RequestParam String paymentId){
		PaymentResponseDto paymentResponseDto = paymentService.getPaymentByPaymentId(paymentId);
		return new ResponseEntity<PaymentResponseDto>(paymentResponseDto,HttpStatus.OK);
	}
	@GetMapping("/getbyOrder")
	public ResponseEntity<PaymentResponseDto> getPayment(@RequestParam int orderId) {
		PaymentResponseDto paymentResponseDto = paymentService.getPaymentByOrderId(orderId);
		return new ResponseEntity<PaymentResponseDto>(paymentResponseDto,HttpStatus.OK);
	}
}
