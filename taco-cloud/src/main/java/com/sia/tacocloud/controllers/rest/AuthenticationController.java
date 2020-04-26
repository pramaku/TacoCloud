package com.sia.tacocloud.controllers.rest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sia.tacocloud.data.jpa.UserRepository;
import com.sia.tacocloud.dto.User;

@RestController
@RequestMapping("/authenticate")
@Profile("rest")
public class AuthenticationController
{
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<?> authenticate(@RequestBody User user)
	{
		UUID.randomUUID();
		final String sessionToken = UUID.randomUUID().toString();
		Optional<com.sia.tacocloud.models.User> userOp = userRepo.findByUsername(user.getUser());
		if (userOp.isPresent())
		{
			com.sia.tacocloud.models.User regUser = userOp.get();
			if (regUser.getActive() == 1)
			{
				regUser.setSessionId(sessionToken);
				userRepo.save(regUser);
			}
			return ResponseEntity.status(HttpStatus.CREATED).header("auth", sessionToken).build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
