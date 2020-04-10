package com.sia.tacocloud.data.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacocloud.models.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	Optional<User> findByUsername(String username);
}
