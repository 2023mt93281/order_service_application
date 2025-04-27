package com.dominos.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominos.orders.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUserId(Integer userId);
	Order findByOrderId(Integer orderId);

}
