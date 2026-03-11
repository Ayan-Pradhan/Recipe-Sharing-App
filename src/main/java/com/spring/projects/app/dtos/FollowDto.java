package com.spring.projects.app.dtos;

import com.spring.projects.app.entites.User;

import lombok.Data;

@Data
public class FollowDto {
	
	private User follower;
	private User following;

}
