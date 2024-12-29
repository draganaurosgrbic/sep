package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class CategoryService {

	private final CategoryRepository repo;

	public List<Category> read() {
		log.info("CategoryService - read");
		return repo.findAll();
	}

}
