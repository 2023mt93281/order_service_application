package com.dominos.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominos.orders.dto.OrderItemDto;
import com.dominos.orders.dto.OrderItemResponseDto;
import com.dominos.orders.dto.UpdateOrderItemDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.OrderItem;

@Service
public interface OrderItemService {
	List<OrderItem> addItems(List<OrderItemDto> orderItems, Order order);
	List<OrderItemResponseDto> addNewItems(List<OrderItemDto> orderItems,int orderId);
	OrderItemResponseDto updateOrderItem(UpdateOrderItemDto updateOrderItemDto);
	String removeOrderItem(int orderItemId);
	List<OrderItemResponseDto> getAllOrderItems(int orderId);
	OrderItemResponseDto getOrderItem(int orderItemId);
}
