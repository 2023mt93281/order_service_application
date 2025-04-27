package com.dominos.orders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominos.orders.dto.CompleteOrderResponseDto;
import com.dominos.orders.dto.OrderRequestDto;
import com.dominos.orders.dto.OrderResponseDto;
import com.dominos.orders.dto.UpdateOrderRequestDto;
import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.OrderItem;
import com.dominos.orders.entity.Payment;
import com.dominos.orders.enums.OrderStatusEnum;
import com.dominos.orders.repository.OrderRepository;
import com.dominos.orders.utils.OrderUtils;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderUtils orderUtils;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	PaymentService paymentService;

	@Override
	public CompleteOrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
		Order orderRequest = orderUtils.prepareCreateOrderData(orderRequestDto);
		// add order data
		Order order = orderRepository.save(orderRequest);
		// add order items
		List<OrderItem> orderItems =  orderItemService.addItems(orderRequestDto.getItems(), order);
		// add Payment details
		Payment payment = paymentService.addPaymentDetails(order, orderRequestDto.getPaymentMethod());
		// convert entity to responsedto
		CompleteOrderResponseDto orderResponse = orderUtils.convertEntityToResponseDTO(order, orderItems, payment);
		return orderResponse;
	}

	@Override
	public String updateOrderStatus(int orderId, String orderStatus) {
		Order order = orderRepository.findByOrderId(orderId);
		order.setOrderStatus(OrderStatusEnum.valueOf(orderStatus));
		orderRepository.save(order);
		return new String("Updated the order successfully");
	}

	@Override
	public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
		int orderId = updateOrderRequestDto.getOrderId();
		Order order = orderRepository.findByOrderId(orderId);
		order.setShippingAddress((null != updateOrderRequestDto.getShippingAddress()? updateOrderRequestDto.getShippingAddress():order.getShippingAddress()));
		order.setOrderDate((null != updateOrderRequestDto.getOrderedDate()? updateOrderRequestDto.getOrderedDate():order.getOrderDate()));
		order.setTotalAmount((null != updateOrderRequestDto.getTotalAmount()?updateOrderRequestDto.getTotalAmount():order.getTotalAmount()));
		order = orderRepository.save(order);
		OrderResponseDto orderResponse = orderUtils.convertOrderEntityToDto(order);
		return orderResponse;
	}

	@Override
	public OrderResponseDto getOrder(int orderId) {
		Order order = findOrder(orderId);
		OrderResponseDto orderResponseDto = orderUtils.convertOrderEntityToDto(order);
		return orderResponseDto;
	}

	@Override
	public Order findOrder(int orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		return order;
	}

	@Override
	public List<OrderResponseDto> getAllOrders(int userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		List<OrderResponseDto> orderResponseList = new ArrayList<>();
		for(Order order:orders) {
			OrderResponseDto orderResponse = orderUtils.convertOrderEntityToDto(order);
			orderResponseList.add(orderResponse);
		}
		return orderResponseList;
	}

}
