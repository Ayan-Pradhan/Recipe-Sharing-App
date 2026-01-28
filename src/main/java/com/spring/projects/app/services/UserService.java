package com.spring.projects.app.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.LoginDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.dtos.UserDto;
import com.spring.projects.app.entites.User;
import com.spring.projects.app.exception.UserNotFoundException;
import com.spring.projects.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepo;
	private final TokenService tokenService;
	private final ModelMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public ResponseDto addUser(UserDto user) {
		Optional<User> existing = userRepo.findById(user.getEmail());
		
		if(existing.isPresent()) return new ResponseDto(ResponseStatusCode.FAILED, "User Already Exists", null);
		
		User mappedUser = mapper.map(user, User.class);
		mappedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Registration Successful", mapper.map(userRepo.save(mappedUser), UserDto.class));
	}
	
	
	public ResponseDto login(LoginDto credentials) {
		Optional<User> existing = userRepo.findById(credentials.getEmail());
		
		if(existing.isPresent() && passwordEncoder.matches(credentials.getPassword(), existing.get().getPassword())) {
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Login Sucess", tokenService.token(existing.get()));
		}
		
		throw new UserNotFoundException("Login Failed");
	}
	
	public User getLoggedInUserDetails() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepo.findById(username);
		if (user.isPresent())
			return user.get();
		throw new UserNotFoundException("User with this particular username is not found");
	}

}
