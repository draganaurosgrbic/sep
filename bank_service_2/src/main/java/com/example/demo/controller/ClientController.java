package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Customer;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService service;

	@PostMapping
	public ResponseEntity<Client> create(@RequestBody Customer dto) {
		return ResponseEntity.ok(service.save(new Client(dto)));
	}

}
