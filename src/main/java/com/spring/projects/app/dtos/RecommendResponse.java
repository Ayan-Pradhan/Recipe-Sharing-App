package com.spring.projects.app.dtos;

import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendResponse {
	
	@Size(min = 1, message = "Title can't be empty")
	private String title;
	
	@Size(min = 20, max = 200, message = "Description shouldn't be greater than 200 chars")
	private String description;
	
	@Size(min = 1, message = "Please mention cooking time")
	private String cookingTime;
	
	@Size(min = 1, message = "Select the category")
	private String category;
	
	@Size(min = 2, message = "Minimum 2 ingredients are required for a recipe")
	private List<String> ingredients;
	
	@Size(min=10, message ="Kindly mention all the steps")
	private List<String> steps;
}
