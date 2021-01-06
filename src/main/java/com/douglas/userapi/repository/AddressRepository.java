package com.douglas.userapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.douglas.userapi.model.Address;
import com.google.common.base.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByCepAndStreetAndNumberAndComplement(String cep,String street,String number,String complement);

//	Optional<Address> findByCepAndStreetAndNumber(String cep, String street, String number);

}
