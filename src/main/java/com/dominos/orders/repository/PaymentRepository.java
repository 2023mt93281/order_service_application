package com.dominos.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominos.orders.entity.Order;
import com.dominos.orders.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,String>{
	Payment findByOrder(Order order);
	Payment findByPaymentId(String paymentId);
}
