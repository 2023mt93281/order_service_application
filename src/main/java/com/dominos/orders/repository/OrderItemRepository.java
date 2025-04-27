package com.dominos.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	List<OrderItem> findByOrder(Order order);
	OrderItem findByOrderItemId(int orderItemId);
}
