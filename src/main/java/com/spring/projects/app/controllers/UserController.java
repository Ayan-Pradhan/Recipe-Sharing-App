package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projects.app.dtos.LoginDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.dtos.UserDto;
import com.spring.projects.app.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users/auth")
public class UserController {
	
	private final UserService service;

	@PostMapping("/sign-up")
	public ResponseEntity<ResponseDto> register(@Valid @RequestBody UserDto user) {
		return ResponseEntity.ok(service.addUser(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto credentials) {
		return ResponseEntity.ok(service.login(credentials));
	}
}
