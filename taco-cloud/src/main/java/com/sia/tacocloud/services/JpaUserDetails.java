package com.sia.tacocloud.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sia.tacocloud.models.User;

public class JpaUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String role;
	private boolean isActive;

	public JpaUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.isActive = user.getActive() == 1;
	}

	@SuppressWarnings("serial")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Arrays.asList(new SimpleGrantedAuthority(this.role));
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public String getUsername()
	{
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isActive;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
