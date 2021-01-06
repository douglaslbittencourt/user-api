package com.douglas.userapi.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.userapi.model.Address;
import com.douglas.userapi.repository.AddressRepository;
import com.douglas.userapi.repository.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Address edit(Address address) {
		if (address.getId() == null) {
			throw new NoSuchElementException("Id not informed");
		}
		
		if (userRepository.countByAddressId(address.getId()) > 1 ) {
			address.setId(null);
			return repository.save(address);
		}

		Address oldAddress = findById(address.getId());
		address.setCreateDate(oldAddress.getCreateDate());
		address.setUpdateDate(LocalDate.now());
		return repository.save(address);
	}

	@Transactional
	public Address save(Address address) {
		Address existAddres = repository.findByCepAndStreetAndNumberAndComplement(address.getCep(), address.getStreet(),
				address.getNumber(), address.getComplement());
		
		if (existAddres != null) {
			return existAddres;
		}
		
		address.setCreateDate(LocalDate.now());
		return repository.save(address);
	}

	@Transactional
	public void deleteById(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Address findById(Long id) {
		Optional<Address> optionalAddress = repository.findById(id);

		if (!optionalAddress.isPresent())
			throw new NoSuchElementException(String.format("id : %o is invalid", id));

		return optionalAddress.get();
	}

}
