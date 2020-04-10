package com.sia.tacocloud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sia.tacocloud.data.jpa.UserRepository;
import com.sia.tacocloud.models.User;

@Service
public class JpaUserDetailsService implements UserDetailsService
{
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> user = userRepo.findByUsername(username);
		if (user.isPresent())
		{
			return new JpaUserDetails(user.get());
		}
		else
		{
			throw new UsernameNotFoundException("User " + username + " doesnot exist");
			
		}
	}
}
