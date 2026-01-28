package com.spring.projects.app.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.dtos.ReviewDto;
import com.spring.projects.app.entites.Review;
import com.spring.projects.app.exception.RecipeNotExistsException;
import com.spring.projects.app.exception.RecordNotFoundException;
import com.spring.projects.app.repositories.RecipeRepository;
import com.spring.projects.app.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepo;
	private final RecipeRepository recipeRepo;
	
	private final ModelMapper mapper;
	
	private final UserService userService;
	
	public ResponseDto addReview(ReviewDto review, Long recipeId) {
		return recipeRepo.findById(recipeId).map(recipe->{
			Review mappedReview = mapper.map(review, Review.class);
			mappedReview.setRecipe(recipe);
			mappedReview.setUser(userService.getLoggedInUserDetails());
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Review added", mapper.map(reviewRepo.save(mappedReview), ReviewDto.class));
		})
		.orElseThrow(()->new RecipeNotExistsException("Recipe with id "+recipeId+"not exists"));	
	}
	
	public ResponseDto getReviews(Long recipeId) {
		return recipeRepo.findById(recipeId).map(recipe->{
			List<ReviewDto> dtos = mapper.map(reviewRepo.findByRecipe(recipe), new TypeToken<List<ReviewDto>>() {}.getType());
			if(dtos.isEmpty()) 
				throw new RecordNotFoundException("No reviews found for this recipe");
			return new ResponseDto(ResponseStatusCode.SUCCESS,"Reviews found", dtos);
		})
		.orElseThrow(()->new RecipeNotExistsException("Recipe with id "+recipeId+"not exists"));
	}
}
