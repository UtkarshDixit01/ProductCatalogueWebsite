package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class ProductsController {

	@Autowired
	private ProductsRepository productsRepository;

	// get all products
	@GetMapping("/products")
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}
	
	// create product rest api
	@PostMapping("/products")
	public Products createProducts(@RequestBody Products products) {
		return productsRepository.save(products);
	}

	// get product by id rest api
	@GetMapping("/products/{id}")
	public ResponseEntity<Products> getProductsById(@PathVariable Long id) {
		Products products = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		return ResponseEntity.ok(products);
	}

	// update product rest api
	@PutMapping("/products/{id}")
	public ResponseEntity<Products> updateProducts(@PathVariable Long id, @RequestBody Products productsDetail) {
		Products products = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		products.setName(productsDetail.getName());
		products.setBrand(productsDetail.getBrand());
		products.setPrice(productsDetail.getPrice());
		products.setImage(productsDetail.getImage());

		Products updatedProduct = productsRepository.save(products);
		return ResponseEntity.ok(updatedProduct);
	}

	// delete product rest api
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProducts(@PathVariable Long id) {
		Products product = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		productsRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
