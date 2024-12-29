package com.example.demo.dto;

import com.example.demo.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderStatusUpdate {
	private PaymentStatus status;
}