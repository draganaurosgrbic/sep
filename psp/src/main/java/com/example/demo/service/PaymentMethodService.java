package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentMethod;
import com.example.demo.repository.PaymentMethodRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class PaymentMethodService {

	private final PaymentMethodRepository repo;
	private final UserService userService;
	private final DiscoveryClient discoveryClient;

	public List<PaymentMethod> read() {
		log.info("PaymentMethodService - read");
		return repo.findAll();
	}

	public PaymentMethod save(PaymentMethod paymentMethod) {
		log.info("PaymentMethodService - save: id=" + paymentMethod.getId());
		return repo.save(paymentMethod);
	}

	public void delete(Long id) {
		log.info("PaymentMethodService - delete: id=" + id);
		repo.deleteById(id);
	}

	public List<String> toAdd() {
		log.info("PaymentMethodService - getAllEurekaServices");
		return discoveryClient.getServices().stream()
				.filter(item -> !item.startsWith("webshop")
						&& !read().stream().map(pm -> pm.getName()).collect(Collectors.toList()).contains(item))
				.collect(Collectors.toList());
	}

	public List<PaymentMethod> getPaymentMethods(String merchantApiKey) {
		log.info("PaymentMethodService - getPaymentMethods: merchantApiKey=" + merchantApiKey.toString());
		return userService.findByApiKey(merchantApiKey).getMethods().stream().collect(Collectors.toList());
	}

}
