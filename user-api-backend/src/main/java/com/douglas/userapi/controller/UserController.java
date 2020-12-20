package com.douglas.userapi.controller;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.douglas.userapi.exception.EntityAlreadyRegisteredException;
import com.douglas.userapi.model.User;
import com.douglas.userapi.service.UserService;
import com.douglas.userapi.utils.CustomErrorType;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService service;

	@ApiOperation(value = "Create User", notes = "Create user by json data")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		try {
			return ResponseEntity.ok().body(service.save(user));
		} catch (ConstraintViolationException | EntityAlreadyRegisteredException ex) {
			return new ResponseEntity<>(new CustomErrorType(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete User", notes = "Delete user by id paramter")
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<>(new CustomErrorType(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Edit User", notes = "if the user exists edit it by jason data")
	@PutMapping
	public ResponseEntity<?> edit(@RequestBody User user) {
		try {
			return ResponseEntity.ok().body(service.edit(user));
		} catch (ConstraintViolationException | NoSuchElementException ex) {
			return new ResponseEntity<>(new CustomErrorType(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Find All Users", notes = "Find all users in database")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

}
