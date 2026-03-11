package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projects.app.dtos.FollowDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.services.FollowService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follower")
public class FollowController {
	
	private final FollowService followService;
	
	@PostMapping("/follow")
	public ResponseEntity<ResponseDto> follow(@RequestBody FollowDto followDto){
		return ResponseEntity.ok(followService.addFollower(followDto));
	}
	
	@DeleteMapping("/unfollow")
	public ResponseEntity<ResponseDto> unfollow(){
		
	}

}
