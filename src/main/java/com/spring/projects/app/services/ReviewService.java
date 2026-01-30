package com.spring.projects.app.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.dtos.ReviewDto;
import com.spring.projects.app.entites.Review;
import com.spring.projects.app.entites.User;
import com.spring.projects.app.exception.RecipeNotExistsException;
import com.spring.projects.app.exception.RecordNotFoundException;
import com.spring.projects.app.repositories.RecipeRepository;
import com.spring.projects.app.repositories.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepo;
	private final RecipeRepository recipeRepo;
	
	private final ModelMapper mapper;
	
	private final UserService userService;
	private final IdGenerationService idGenerationService;
	
	public ResponseDto get(String recipeId) {
		return recipeRepo.findByExtId(recipeId).map(recipe->{
			List<ReviewDto> dtos = mapper.map(reviewRepo.findByRecipe(recipe), new TypeToken<List<ReviewDto>>() {}.getType());
			if(dtos.isEmpty()) 
				throw new RecordNotFoundException("No reviews found for this recipe");
			return new ResponseDto(ResponseStatusCode.SUCCESS,"Reviews found", dtos);
		})
		.orElseThrow(()->new RecipeNotExistsException("Recipe with id "+recipeId+"not exists"));
	}
	
	@Transactional
	public ResponseDto add(ReviewDto review, String recipeId) {
		return recipeRepo.findByExtId(recipeId).map(recipe->{
			User user = userService.getLoggedInUserDetails();
			Review mappedReview = mapper.map(review, Review.class);
			mappedReview.setExtId(idGenerationService.generate());
			mappedReview.setRecipe(recipe);
			mappedReview.setUsername(user.getEmail());
			mappedReview.setUser(user);
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Review added", mapper.map(reviewRepo.save(mappedReview), ReviewDto.class));
		})
		.orElseThrow(()->new RecipeNotExistsException("Recipe with id "+recipeId+"not exists"));	
	}
	
	@Transactional
	public ResponseDto delete(String extId) {
		int status = reviewRepo.deleteByExtIdAndUser(extId, userService.getLoggedInUserDetails());
		if(status == 1)
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Review deleted successfully", "[]");
		throw new RecordNotFoundException("Review with id "+extId+" not exist");
	}
	
	
	
	
}
