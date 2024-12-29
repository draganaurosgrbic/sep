package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Service
@Log4j2
public class RoleService {

	private final RoleRepository repo;

	public List<Role> read() {
		log.info("RoleService - read");
		return repo.findAll();
	}

}
