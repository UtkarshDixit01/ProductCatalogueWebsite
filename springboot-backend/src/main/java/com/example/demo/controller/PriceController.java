package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class PriceController {

	@Autowired
	private PriceRepository priceRepository;

	// get all prices
	@GetMapping("/products/prices")
	public List<Price> getAllPrices() {
		return priceRepository.findAll();

	}

	// get price by id rest api
	@GetMapping("/products/prices/{id}")
	public ResponseEntity<Price> getPriceById(@PathVariable Long id) {
		Price price = priceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Price not exist with id :" + id));
		return ResponseEntity.ok(price);
	}

}
