package com.douglas.userapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.douglas.userapi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
