package com.spring.projects.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRequest {
	
	// add validations here
	private String mood;
	private String cuisine;
	private String type;			// vegan, vegeterian, non-veg etc
	private String category;		// starter/ dessert/ main course etc
	private String cookingTime;

}
