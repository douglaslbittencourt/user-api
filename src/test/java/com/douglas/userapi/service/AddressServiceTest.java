package com.douglas.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.douglas.userapi.model.Address;
import com.douglas.userapi.model.User;
import com.douglas.userapi.repository.AddressRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
	
	@InjectMocks
	private AddressService service;
	
	@Mock
	private AddressRepository mockRepository;
	
	@Test
	public void editTest() {
		Address newAddress = createAddress(1L);
		newAddress.setStreet("test Street");
		newAddress.setCreateDate(LocalDate.now());
		newAddress.setUpdateDate(LocalDate.now());
		
		Address oldAddress = createAddress(1L);
		oldAddress.setCreateDate(LocalDate.now());
		
		Optional<Address> optionalOldAddress = Optional.of(oldAddress);
		
		when(mockRepository.findById(1L)).thenReturn(optionalOldAddress);
		when(mockRepository.save(oldAddress)).thenReturn(newAddress);
		
		assertEquals(newAddress, service.edit(oldAddress));
	}
	
	@Test
	public void editWithoutIdTest() {
		Address address = createAddress(null);
		
		assertThrows(RuntimeException.class, () -> {
			service.edit(address);
		});
		
	}
	
	@Test
	public void saveTest() {
		Address address = createAddress(null);
		address.setCreateDate(LocalDate.now());
		
		Address newAddress = createAddress(1L);
		newAddress.setCreateDate(LocalDate.now());
		
		when(mockRepository.save(address)).thenReturn(newAddress);
		
		assertEquals(newAddress, service.save(address));
	}
	
	@Test
	public void saveExistAddressTest() {
		Address address = createAddress(null);
		address.setCreateDate(LocalDate.now());
		
		Address existAddres = createAddress(1L);
		existAddres.setCreateDate(LocalDate.now());
		
		when(mockRepository.findByCepAndStreetAndNumberAndComplement(address.getCep(), address.getStreet(),
				address.getNumber(), address.getComplement())).thenReturn(existAddres);
		
		assertEquals(existAddres, service.save(address));
	}
	
	@Test
	public void deleteByIdTest() {
		Address address = createAddress(1L);
		Optional<Address> optionalAddress = Optional.of(address);
		
		when(mockRepository.findById(1L)).thenReturn(optionalAddress);
		
		service.deleteById(1L);
		verify(mockRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void findByIdWithNonexistentUserTest() {
		Optional<Address> optionalAddress = Optional.empty();
		
		when(mockRepository.findById(1L)).thenReturn(optionalAddress);
		
		assertThrows(RuntimeException.class, () -> {
			service.findById(1L);
		});
	}
	
	private Address createAddress(Long id) {
		return new Address(id, "Rua Teste", "123", null, "São Paulo", "São Paulo", "12345-123");
	}
	
}
