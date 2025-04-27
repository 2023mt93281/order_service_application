package com.dominos.orders.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dominos.orders.dto.OrderItemDto;
import com.dominos.orders.dto.OrderItemResponseDto;
import com.dominos.orders.dto.UpdateOrderItemDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.OrderItem;
import com.dominos.orders.repository.OrderItemRepository;
import com.dominos.orders.utils.OrderUtils;
@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	OrderUtils orderUtils;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	@Lazy
	OrderService orderService;
	@Override
	public List<OrderItem> addItems(List<OrderItemDto> orderItems, Order order) {
		List<OrderItem> orderItemsList = new ArrayList<>();
		for(OrderItemDto orderItemDto:orderItems) {
			OrderItem orderItem = orderUtils.prepareOrderItemData(orderItemDto, order);
			orderItemsList.add(orderItem);
		}
		orderItemRepository.saveAll(orderItemsList);
		return orderItemsList;
	}
	@Override
	public List<OrderItemResponseDto> addNewItems(List<OrderItemDto> orderItems, int orderId) {
		Order order = orderService.findOrder(orderId);
		List<OrderItem> orderItemsResponseList = addItems(orderItems, order);
		List<OrderItemResponseDto> orderItemsResponseListDto = new ArrayList<>();
		for(OrderItem orderItemResponse:orderItemsResponseList) {
			OrderItemResponseDto orderItemResponseDto = orderUtils.convertOrderItemToResponseDto(orderItemResponse);
			orderItemsResponseListDto.add(orderItemResponseDto);
		}
		return orderItemsResponseListDto;
	}
	@Override
	public OrderItemResponseDto updateOrderItem(UpdateOrderItemDto updateOrderItemDto) {
		OrderItem orderItem = orderItemRepository.findByOrderItemId(updateOrderItemDto.getOrderItemId());
		orderItem.setQuantity((updateOrderItemDto.getQuantity()!=0)?updateOrderItemDto.getQuantity():orderItem.getQuantity());
		orderItem.setUnitPrice(null != updateOrderItemDto.getUnitPrice()?updateOrderItemDto.getUnitPrice():orderItem.getUnitPrice());
		orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
		OrderItem orderItemresponse = orderItemRepository.save(orderItem);
		OrderItemResponseDto orderItemResponseDto = orderUtils.convertOrderItemToResponseDto(orderItemresponse);
		return orderItemResponseDto;
	}
	@Override
	public String removeOrderItem(int orderItemId) {
		OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId);
		orderItemRepository.delete(orderItem);
		String response = "Removed Order item successfully";
		return response;
	}
	@Override
	public List<OrderItemResponseDto> getAllOrderItems(int orderId) {
		Order order = orderService.findOrder(orderId);
		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		List<OrderItemResponseDto> orderItemResponses = new ArrayList<>();
		for(OrderItem orderItem:orderItems) {
			OrderItemResponseDto orderItemResponseDto = orderUtils.convertOrderItemToResponseDto(orderItem);
			orderItemResponses.add(orderItemResponseDto);
		}
		return orderItemResponses;
	}
	@Override
	public OrderItemResponseDto getOrderItem(int orderItemId) {
		OrderItem orderItem = orderItemRepository.findByOrderItemId(orderItemId);
		OrderItemResponseDto orderItemResponseDto = orderUtils.convertOrderItemToResponseDto(orderItem);
		return orderItemResponseDto;
	}

}
