package com.spring.projects.app.services;


import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class IdGenerationService {

	private static final SecureRandom random = new SecureRandom();
	private static final String CHARS = "ABCDEFGHIJKLM7894561230mnopqrstuvwxyz0123456789abcdefghijkl321654987NOPQRSTUVWXYZ";
	
	public String generate() {
		StringBuilder id = new StringBuilder(); 
		for(int i=0;i<6;i++) {
			id.append(CHARS.charAt(random.nextInt(0, 81)));
		}
		return id.toString();
	}
	
}
