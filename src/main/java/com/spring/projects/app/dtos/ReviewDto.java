package com.spring.projects.app.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
	private String id;
	private String username;
	
	@NotEmpty(message = "Review must not be empty")
	private String review;
}
