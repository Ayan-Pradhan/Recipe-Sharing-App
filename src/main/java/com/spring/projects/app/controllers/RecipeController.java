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
import com.spring.projects.app.dtos.ReviewDto;
import com.spring.projects.app.services.FavouritesService;
import com.spring.projects.app.services.RecipeService;
import com.spring.projects.app.services.RecommendService;
import com.spring.projects.app.services.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RecipeController {
	
	private final RecipeService recipes;
	private final RecommendService recommendations;
	private final ReviewService reviews;
	private final FavouritesService favourites;

	@GetMapping("recipes") 			// add pagination 
	public ResponseEntity<ResponseDto> getAllRecipe() {
		return ResponseEntity.ok(recipes.getAll());
	}
	
	@GetMapping("/recipes/{id}")
	public ResponseEntity<ResponseDto> getRecipe(@PathVariable Long id) {
		return ResponseEntity.ok(recipes.get(id));
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<ResponseDto> addRecipe(@Valid @RequestBody RecipeDto recipe) {
		return ResponseEntity.ok(recipes.add(recipe));
	}
	
	@PatchMapping("/recipes/{id}")
	public ResponseEntity<ResponseDto> updateRecipe(@RequestBody RecipeDto recipe,@PathVariable Long id) {
		return ResponseEntity.ok(recipes.edit(recipe, id));
	}
	
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<ResponseDto> deleteRecipe(@PathVariable Long id) {
		return ResponseEntity.ok(recipes.delete(id));
	}
	
	@PostMapping("recipes/reviews/{recipeId}")
	public ResponseEntity<ResponseDto> reviewRecipe(@RequestBody ReviewDto review, @PathVariable Long recipeId) {
		return ResponseEntity.ok(reviews.addReview(review, recipeId));
	}
	
	@GetMapping("recipes/reviews/{recipeId}")
	public ResponseEntity<ResponseDto> reviewRecipe(@PathVariable Long recipeId) {
		return ResponseEntity.ok(reviews.getReviews(recipeId));
	}
	
	@GetMapping("/recipes/favourites/{recipeId}")
	public ResponseEntity<ResponseDto> addFavourite(@PathVariable Long recipeId) {
		return ResponseEntity.ok(favourites.addToFavourites(recipeId));
	}
	
	@GetMapping("recipes/favourites")
	public ResponseEntity<ResponseDto> favourites(){
		return ResponseEntity.ok(favourites.getFavourites());
	}
	
	@GetMapping("recipes/search/{keyword}")
	public ResponseEntity<ResponseDto> searchRecipe(@RequestBody String keyword) {
		return null;
	}
	
	@PostMapping("recipes/recommend")
	public ResponseEntity<ResponseDto> recommendRecipe(@RequestBody RecommendRequest details) {
		return ResponseEntity.ok(recommendations.getRecommendedRecipes(details));
	}
	
}
