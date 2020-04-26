package com.sia.tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sia.tacocloud.data.jpa.UserRepository;
import com.sia.tacocloud.models.User;

@Controller
@RequestMapping("/register")
@Profile("web")
public class RegistrationController
{
	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder encoder;
	@GetMapping
	public String showRegistrationForm()
	{
		return "register";
	}

	@PostMapping
	public String registerUser(@ModelAttribute User user)
	{
		user.setRole("ROLE_USER");
		user.setActive(1);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/login";
	}
}
