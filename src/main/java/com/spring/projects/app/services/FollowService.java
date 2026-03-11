package com.spring.projects.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.FollowDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.entites.Follow;
import com.spring.projects.app.repositories.FollowRepository;

import lombok.RequiredArgsConstructor;

// work in progress

@RequiredArgsConstructor
@Service
public class FollowService {
	
	private final FollowRepository followRepo;
	
	private final ModelMapper mapper;
	
	public ResponseDto addFollower(FollowDto followDto) {
		Follow followDetails = mapper.map(followDto, Follow.class);
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Following Successfully", mapper.map(followRepo.save(followDetails), FollowDto.class));
	}
	
	public void unfollow() {
		
	}
	
}
