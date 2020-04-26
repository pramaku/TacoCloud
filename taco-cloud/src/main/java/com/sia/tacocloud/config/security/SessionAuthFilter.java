package com.sia.tacocloud.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sia.tacocloud.data.jpa.UserRepository;
import com.sia.tacocloud.models.User;
import com.sia.tacocloud.services.JpaUserDetailsService;

@Component
@Profile("rest")
public class SessionAuthFilter extends OncePerRequestFilter
{
	@Autowired
	UserRepository userRepo;
	@Autowired
	JpaUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException
	{
		String sessionToken = request.getHeader("token");
		if (sessionToken != null && !sessionToken.isEmpty())
		{
			User user = getUserFromToken(sessionToken);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UsernamePasswordAuthenticationToken userPassAuthToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	private User getUserFromToken(String sessionToken)
	{
		for(User user: userRepo.findAllBySessionId(sessionToken))
		{
			return user;
		}
		return null;
	}

}
