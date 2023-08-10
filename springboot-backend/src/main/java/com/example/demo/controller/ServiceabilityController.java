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
import com.example.demo.model.Serviceability;
import com.example.demo.repository.ServiceabilityRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class ServiceabilityController {

	@Autowired
	private ServiceabilityRepository serviceabilityRepository;

	// get all products serviceabilty api
	@GetMapping("/products/serviceability")
	public List<Serviceability> getAllProductsServiceability() {
		return serviceabilityRepository.findAll();
	}
	
	// get product serviceability by id rest api
		@GetMapping("/products/serviceability/{id}")
		public ResponseEntity<Serviceability> getProductsById(@PathVariable Long id) {
			Serviceability serv = serviceabilityRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Serviceability not exist with id :" + id));
			return ResponseEntity.ok(serv);
		}

}
