package com.dominos.orders.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dominos.orders.dto.CompleteOrderResponseDto;
import com.dominos.orders.dto.OrderItemDto;
import com.dominos.orders.dto.OrderItemResponseDto;
import com.dominos.orders.dto.OrderRequestDto;
import com.dominos.orders.dto.OrderResponseDto;
import com.dominos.orders.dto.PaymentResponseDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.OrderItem;
import com.dominos.orders.entity.Payment;
import com.dominos.orders.enums.OrderStatusEnum;
import com.dominos.orders.enums.PaymentMethod;
@Component
public class OrderUtils {
	public Order prepareCreateOrderData(OrderRequestDto orderRequestDto) {
		Order order = new Order();
		order.setUserId(orderRequestDto.getUserId());
		order.setOrderDate(orderRequestDto.getOrderedDate());
		order.setShippingAddress(orderRequestDto.getShippingAddress());
		order.setTotalAmount(orderRequestDto.getTotalAmount());
		if(orderRequestDto.getPaymentMethod()==PaymentMethod.Cash) {
			order.setOrderStatus(OrderStatusEnum.CONFIRMED);
		}
		else {
			order.setOrderStatus(OrderStatusEnum.PENDING);
		}
		return order;
		
	}
	
	public OrderItem prepareOrderItemData(OrderItemDto orderItemDto, Order order) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setProductId(orderItemDto.getProductId());
		orderItem.setQuantity(orderItemDto.getQuantity());
		orderItem.setUnitPrice(orderItemDto.getUnitPrice());
		orderItem.setTotalPrice(orderItemDto.getUnitPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity())) );
		orderItem.setItemName(orderItemDto.getItemName());
		return orderItem;
	}
	
	public CompleteOrderResponseDto convertEntityToResponseDTO(Order order,List<OrderItem> orderItems,Payment payment) {
		CompleteOrderResponseDto orderResponse = new CompleteOrderResponseDto();
		List<OrderItemResponseDto> orderItemsresponse = new ArrayList<>();
		for(OrderItem orderItemResponse:orderItems) {
			OrderItemResponseDto orderItemResponseDto = convertOrderItemToResponseDto(orderItemResponse);
			orderItemsresponse.add(orderItemResponseDto);
		}
		PaymentResponseDto paymentResponseDto = convertPaymentEntityToResponseDto(payment);
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setUserId(order.getUserId());
		orderResponse.setTotalAmount(order.getTotalAmount());
		orderResponse.setShippingAddress(order.getShippingAddress());
		orderResponse.setCreatedAt(order.getCreatedAt());
		orderResponse.setUpdatedAt(order.getUpdatedAt());
		orderResponse.setOrderStatus(order.getOrderStatus());
		orderResponse.setOrderItems(orderItemsresponse);
		orderResponse.setPaymentDetails(paymentResponseDto);
		return orderResponse;
	}
	
	public OrderResponseDto convertOrderEntityToDto(Order order) {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(order.getOrderId());
		orderResponseDto.setUserId(order.getUserId());
		orderResponseDto.setOrderDate(order.getOrderDate());
		orderResponseDto.setOrderStatus(order.getOrderStatus());
		orderResponseDto.setTotalAmount(order.getTotalAmount());
		orderResponseDto.setShippingAddress(order.getShippingAddress());
		orderResponseDto.setCreatedAt(order.getCreatedAt());
		orderResponseDto.setUpdatedAt(order.getUpdatedAt());
		return orderResponseDto;
	}
	
	public OrderItemResponseDto convertOrderItemToResponseDto(OrderItem orderItem) {
		OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
		orderItemResponseDto.setOrderItemId(orderItem.getOrderItemId());
		orderItemResponseDto.setItemName(orderItem.getItemName());
		orderItemResponseDto.setProductId(orderItem.getProductId());
		orderItemResponseDto.setQuantity(orderItem.getQuantity());
		orderItemResponseDto.setUnitPrice(orderItem.getUnitPrice());
		orderItemResponseDto.setTotalPrice(orderItem.getTotalPrice());
		return orderItemResponseDto;
	}
	
	public PaymentResponseDto convertPaymentEntityToResponseDto(Payment payment) {
		PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
		paymentResponseDto.setPaymentId(payment.getPaymentId());
		paymentResponseDto.setPaymentMethod(payment.getPaymentMethod());
		paymentResponseDto.setPaymentAmount(payment.getPaymentAmount());
		paymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
		paymentResponseDto.setPaymentDate(payment.getPaymentDate());
		return paymentResponseDto;
		
	}
}
