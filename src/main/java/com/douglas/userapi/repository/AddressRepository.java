package com.douglas.userapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.douglas.userapi.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByCepAndStreetAndNumberAndComplement(String cep,String street,String number,String complement);

}
