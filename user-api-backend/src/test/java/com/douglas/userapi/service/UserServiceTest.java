package com.douglas.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.douglas.userapi.model.User;
import com.douglas.userapi.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository mockRepository;
	
	@Test
	public void editTest() {
		User newUser = createUser(1L);
		newUser.setName("Douglas Linhares");
		newUser.setUpdateDate(LocalDate.now());
		newUser.setCreateDate(LocalDate.now());
		
		User oldUser = createUser(1L);
		oldUser.setCreateDate(LocalDate.now());
		
		Optional<User> optionalOldUser = Optional.of(oldUser);	
		
		when(mockRepository.findById(1L)).thenReturn(optionalOldUser);
		when(mockRepository.save(oldUser)).thenReturn(newUser);
		
		assertEquals(newUser, service.edit(oldUser));
		
	}
	
	@Test
	public void editWithoutIdTest() {
		User user = createUser(null);
		
		assertThrows(RuntimeException.class, () -> {
			service.edit(user);
		});
		
	}
	
	@Test
	public void saveTest() {
		User user = createUser(null);
		user.setCreateDate(LocalDate.now());
		
		User newUser = createUser(1L);
		newUser.setCreateDate(LocalDate.now());
		
		when(mockRepository.save(user)).thenReturn(newUser);
		
		assertEquals(newUser, service.save(user));
	}
	
	@Test
	public void deleteByIdTest() {
		User user = createUser(1L);
		Optional<User> optionalUser = Optional.of(user);
		
		when(mockRepository.findById(1L)).thenReturn(optionalUser);
		
		service.deleteById(1L);
		verify(mockRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void deleteByIdWithNonexistentUserTest() {
		Optional<User> optionalUser = Optional.empty();
		
		when(mockRepository.findById(1L)).thenReturn(optionalUser);
		
		assertThrows(RuntimeException.class, () -> {
			service.deleteById(1L);
		});
	}
	
	@Test
	public void findByIdTest() {
		User user = createUser(1L);
		Optional<User> optionalUser = Optional.of(user);
		
		when(mockRepository.findById(1L)).thenReturn(optionalUser);
		
		assertEquals(user, service.findById(1L));
	}
	
	@Test
	public void findByIdWithNonexistentUserTest() {
		Optional<User> optionalUser = Optional.empty();
		
		when(mockRepository.findById(1L)).thenReturn(optionalUser);
		
		assertThrows(RuntimeException.class, () -> {
			service.findById(1L);
		});
	}
	
	@Test
	public void findAllTest() {
		User user1 = createUser(1L);
		user1.setName("Douglas Linhares");
		
		User user2 = createUser(1L);
		
		ArrayList<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		
		Iterable<User> iteratorUsers = users;   
		
		when(mockRepository.findAll()).thenReturn(iteratorUsers);
		
		assertEquals(iteratorUsers, service.findAll());
		
	}
	
	
	private User createUser(Long id) {
		return new User(id,"Douglas","M","douglaslbittencourt@gmail.com",LocalDate.of(1997, 12, 15),"Brasil", "Brasil", "42888068877");
	}
}
