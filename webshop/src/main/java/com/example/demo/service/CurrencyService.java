package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Currency;
import com.example.demo.repository.CurrencyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class CurrencyService {

	private final CurrencyRepository repo;

	public List<Currency> read() {
		log.info("CurrencyService - read");
		return repo.findAll();
	}

}
