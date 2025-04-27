package com.dominos.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominos.orders.dto.CompleteOrderResponseDto;
import com.dominos.orders.dto.OrderRequestDto;
import com.dominos.orders.dto.OrderResponseDto;
import com.dominos.orders.dto.UpdateOrderRequestDto;
import com.dominos.orders.entity.Order;

@Service
public interface OrderService {
	CompleteOrderResponseDto createOrder(OrderRequestDto orderRequestDto);
	String updateOrderStatus(int orderId,String orderStatus);
	OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto);
	OrderResponseDto getOrder(int orderId);
	Order findOrder(int orderId);
	List<OrderResponseDto> getAllOrders(int userId);
}
