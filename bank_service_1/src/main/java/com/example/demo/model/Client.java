package com.example.demo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.dto.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String merchantId;

	@NotBlank
	private String merchantPassword;

	@NotBlank
	private String cardHolder;

	@NotBlank
	private String panNumber;

	@NotBlank
	private String cvv;

	@NotBlank
	private String expirationDate;

	@NotNull
	private Double availableFunds = 0.0;

	@NotNull
	private Double reservedFunds = 0.0;

	public void incAvailableFunds(Double amount) {
		availableFunds += amount;
	}

	public void decAvailableFunds(Double amount) {
		availableFunds -= amount;
	}

	public Client(Customer customer) {
		merchantId = UUID.randomUUID().toString();
		merchantPassword = UUID.randomUUID().toString();
		cardHolder = customer.getCardHolder();
		panNumber = customer.getPanNumber();
		cvv = customer.getCvv();
		expirationDate = customer.getMm() + "/" + customer.getYy();
	}

}
