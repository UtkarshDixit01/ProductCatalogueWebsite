package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	//get all users
	@GetMapping("/users")
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}
	
	//register new_user rest api
	@PostMapping("/users")
	public Users registerUser(@RequestBody Users user) {
		return usersRepository.save(user);
	}
	
	//get user by id rest api
	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable Long id){
		Users user = usersRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :"+ id)); 
		return ResponseEntity.ok(user);
	}

}
