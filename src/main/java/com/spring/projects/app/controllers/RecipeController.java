package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projects.app.dtos.RecipeDto;
import com.spring.projects.app.dtos.RecommendRequest;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.services.RecipeService;
import com.spring.projects.app.services.RecommendService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	
	private final RecipeService recipes;
	private final RecommendService recommendations;

	@GetMapping("recipe/all") 
	public ResponseEntity<ResponseDto> getAllRecipe() {
		return ResponseEntity.ok(recipes.getAll());
	}
	
	@GetMapping("/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> getRecipe(@PathVariable String recipeId) {
		return ResponseEntity.ok(recipes.get(recipeId));
	}
	
	@PostMapping("/recipe")
	public ResponseEntity<ResponseDto> addRecipe(@Valid @RequestBody RecipeDto recipe) {
		return ResponseEntity.ok(recipes.add(recipe));
	}
	
	@PatchMapping("/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> updateRecipe(@RequestBody RecipeDto recipe,@PathVariable String recipeId) {
		return ResponseEntity.ok(recipes.edit(recipe, recipeId));
	}
	
	@DeleteMapping("/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> deleteRecipe(@PathVariable String recipeId) {
		return ResponseEntity.ok(recipes.delete(recipeId));
	}
	
	@PostMapping("/recipe/recommend")
	public ResponseEntity<ResponseDto> recommendRecipe(@RequestBody RecommendRequest details) {
		return ResponseEntity.ok(recommendations.getRecommendedRecipes(details));
	}
	
}
