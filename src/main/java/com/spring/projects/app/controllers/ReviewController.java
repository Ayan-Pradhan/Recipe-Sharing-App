package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.dtos.ReviewDto;
import com.spring.projects.app.services.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private final ReviewService reviews;
	
	@GetMapping("/review/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> getReviews(@PathVariable String recipeId) {
		return ResponseEntity.ok(reviews.get(recipeId));
	}

	@PostMapping("/review/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> addReview(@RequestBody @Valid ReviewDto review, @PathVariable String recipeId) {
		return ResponseEntity.ok(reviews.add(review, recipeId));
	}
	
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<ResponseDto> deleteReview(@PathVariable String reviewId){
		return ResponseEntity.ok(reviews.delete(reviewId));
	}

}
