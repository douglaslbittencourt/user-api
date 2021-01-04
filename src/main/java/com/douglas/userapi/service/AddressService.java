package com.douglas.userapi.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.userapi.model.Address;
import com.douglas.userapi.repository.AddressRepository;

@Service
public class AddressService {

	private AddressRepository repository;

	@Transactional
	public Address edit(Address address) {
		if (address.getId() == null) {
			throw new NoSuchElementException("Id not informed");
		}

		Address oldAddress = findById(address.getId());
		address.setCreateDate(oldAddress.getCreateDate());
		address.setUpdateDate(LocalDate.now());
		return repository.save(address);
	}

	@Transactional
	public Address save(Address address) {
		if (repository.existsByCepAndStreetAndNumberAndComplement(address.getCep(), address.getStreet(),
				address.getNumber(), address.getComplement())) {
			return address;
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

	@Transactional(readOnly = true)
	public Iterable<Address> findAll() {
		return repository.findAll();
	}

}
