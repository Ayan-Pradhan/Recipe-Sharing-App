package com.spring.projects.app.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRequest {
	
	@Size(min = 3, message = "Please tell us how you feel")
	private String mood;
	
	@Size(min = 3, message = "Mention the cuisine you're craving for")
	private String cuisine;
	
	@Size(min = 3, message = "Invalid type of recipe -- Please choose(veg/non-veg/vegan etc)")
	private String type;	     
	
	@Size(min = 3, message = "Invalid category -- Please choose(starter/dessert/main course etc)")
	private String category;	
	
	@Size(min = 1, message = "Cooking time must be mentioned")
	private String cookingTime;

}
