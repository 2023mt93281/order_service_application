package com.dominos.orders.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dominos.orders.enums.OrderStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	@Column(name = "total_amount")
	private BigDecimal totalAmount;
	@Column(name = "shipping_address")
	private String shippingAddress;
	@Column(name = "order_status")
	private OrderStatusEnum orderStatus;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
