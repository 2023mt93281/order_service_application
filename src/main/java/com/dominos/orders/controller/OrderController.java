package com.dominos.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dominos.orders.dto.CompleteOrderResponseDto;
import com.dominos.orders.dto.OrderRequestDto;
import com.dominos.orders.dto.OrderResponseDto;
import com.dominos.orders.dto.UpdateOrderRequestDto;
import com.dominos.orders.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@PostMapping("/create")
	public ResponseEntity<CompleteOrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		CompleteOrderResponseDto order = orderService.createOrder(orderRequestDto);
		return new ResponseEntity<CompleteOrderResponseDto>(order, HttpStatus.CREATED);        
	}
	@PutMapping("/updatestatus")
	public ResponseEntity<String> updateOrderStatus(@RequestParam int orderId,@RequestParam String orderStatus) {
		String response = orderService.updateOrderStatus(orderId, orderStatus);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<OrderResponseDto> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequest) {
		OrderResponseDto orderResponse = orderService.updateOrder(updateOrderRequest);
		return new ResponseEntity<OrderResponseDto>(orderResponse,HttpStatus.OK);
		
	}
	@GetMapping("/get")
	public ResponseEntity<OrderResponseDto> getOrder(@RequestParam int orderId) {
		OrderResponseDto orderResponse = orderService.getOrder(orderId);
		return new ResponseEntity<OrderResponseDto>(orderResponse,HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<OrderResponseDto>> getAllOrders(@RequestParam int userId) {
		List<OrderResponseDto> orderResponses = orderService.getAllOrders(userId);
		return new ResponseEntity<List<OrderResponseDto>>(orderResponses,HttpStatus.OK);
		
	}

}
