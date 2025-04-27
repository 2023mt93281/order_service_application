package com.dominos.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dominos.orders.dto.OrderItemDto;
import com.dominos.orders.dto.OrderItemResponseDto;
import com.dominos.orders.dto.UpdateOrderItemDto;
import com.dominos.orders.service.OrderItemService;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemController {
	@Autowired
	OrderItemService orderItemService;

	@PostMapping("/add")
	public ResponseEntity<List<OrderItemResponseDto>> addNewItems(@RequestBody List<OrderItemDto> orderItems,
			@RequestParam int orderId) {
		List<OrderItemResponseDto> orderItemsResponse = orderItemService.addNewItems(orderItems, orderId);
		return new ResponseEntity<List<OrderItemResponseDto>>(orderItemsResponse, HttpStatus.CREATED);

	}
	@PutMapping("/update")
	public ResponseEntity<OrderItemResponseDto> updateOrderItem(@RequestBody UpdateOrderItemDto updateOrderItemDto) {
		OrderItemResponseDto orderItemResponseDto = orderItemService.updateOrderItem(updateOrderItemDto);
		return new ResponseEntity<OrderItemResponseDto>(orderItemResponseDto, HttpStatus.OK);

	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> removeOrderItem(@RequestParam int orderItemId) {
		String response = orderItemService.removeOrderItem(orderItemId);
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}
	@GetMapping("/getall")
	public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems(@RequestParam int orderId) {
		List<OrderItemResponseDto> orderItemResponseList = orderItemService.getAllOrderItems(orderId);
		return new ResponseEntity<List<OrderItemResponseDto>>(orderItemResponseList,HttpStatus.OK);
		
	}
	@GetMapping("/get")
	public ResponseEntity<OrderItemResponseDto> getOrderItem(@RequestParam int orderItemId) {
		OrderItemResponseDto orderItemResponseDto = orderItemService.getOrderItem(orderItemId);
		return new ResponseEntity<OrderItemResponseDto>(orderItemResponseDto,HttpStatus.OK);
		
	}
}
