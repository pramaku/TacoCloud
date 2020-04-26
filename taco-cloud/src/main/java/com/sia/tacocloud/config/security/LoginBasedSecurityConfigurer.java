package com.sia.tacocloud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("web")
public class LoginBasedSecurityConfigurer extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/design","/orders").hasRole("USER")
			.antMatchers("/", "/**").permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
						.defaultSuccessUrl("/design", true)
			.and()
				.logout()
					.logoutSuccessUrl("/");
		http.csrf().disable();

		//http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new StandardPasswordEncoder("53cr3t");
	}
}
