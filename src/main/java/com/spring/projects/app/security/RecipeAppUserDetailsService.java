package com.spring.projects.app.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.projects.app.entites.User;
import com.spring.projects.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeAppUserDetailsService implements UserDetailsService{

	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findById(username);
		return org.springframework.security.core.userdetails.User
				.withUsername(user.get().getEmail())
				.password(user.get().getPassword())
				.roles(user.get().getRole())
				.build();
		
	}

}
