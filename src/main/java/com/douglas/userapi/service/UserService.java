package com.douglas.userapi.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.userapi.exception.EntityAlreadyRegisteredException;
import com.douglas.userapi.model.User;
import com.douglas.userapi.repository.AddressRepository;
import com.douglas.userapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AddressService addressService;
	
	@Transactional
	public User edit(User user) {
		if (user.getId() == null) {
			throw new NoSuchElementException("Id not informed");
		}
		
		if (user.getAddress() != null) {
			addressService.edit(user.getAddress());
		}
		
		User oldUser = findById(user.getId());
		user.setCreateDate(oldUser.getCreateDate());
		user.setUpdateDate(LocalDate.now());
		return repository.save(user);
	}
	
	@Transactional
	public User save(User user) {
		if (repository.existsByCpf(user.getCpf())) {
			throw new EntityAlreadyRegisteredException("CPF already registered");
		}
		
		if (user.getAddress() != null) {
			user.setAddress(addressService.save(user.getAddress()));
		}
		
		user.setCreateDate(LocalDate.now());
		return repository.save(user);
	}
	
	@Transactional
	public void deleteById(Long id) {
		User user = findById(id);
		if (user.getAddress() != null && repository.countByAddressId(user.getAddress().getId()) == 1) {
			addressService.deleteById(user.getAddress().getId());
		}
		repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		Optional<User> optionalUser = repository.findById(id);
		
		if (!optionalUser.isPresent()) 
			throw new NoSuchElementException(String.format("id : %o is invalid", id));
		
		return optionalUser.get();
	}
	
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return repository.findAll();
	}
	
}
