package com.dominos.orders.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dominos.orders.enums.PaymentMethod;
import com.dominos.orders.enums.PaymentStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
	@Id
	@Column(name = "payment_id")
	private String paymentId;

	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	@Column(name = "payment_status")
	private PaymentStatusEnum paymentStatus;
	@Column(name = "payment_date")
	private LocalDateTime paymentDate;
	@Column(name = "payment_method")
	private PaymentMethod paymentMethod;
	@Column(name = "payment_amount")
	private BigDecimal paymentAmount;

}
