package com.spring.projects.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.RecipeDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.entites.Recipe;
import com.spring.projects.app.exception.RecipeNotExistsException;
import com.spring.projects.app.repositories.RecipeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeService {

	private final RecipeRepository recipeRepo;
	
	private final ModelMapper mapper;
	
	private final UserService userService;
	private final IdGenerationService idGenerationService;

	@Transactional
	public ResponseDto add(RecipeDto recipe) {
		Recipe mappedRecipe = mapper.map(recipe, Recipe.class);
		mappedRecipe.setExtId(idGenerationService.generate());
		mappedRecipe.setUser(userService.getLoggedInUserDetails());
		mappedRecipe.setTimestamp(LocalDateTime.now());
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Added Successfully", mapper.map(recipeRepo.save(mappedRecipe), RecipeDto.class));
	}

	@Transactional
	public ResponseDto edit(RecipeDto recipe, String extId) {
		return recipeRepo.findByExtIdAndUser(extId, userService.getLoggedInUserDetails()).map((existing) -> {
			mapper.map(recipe, existing);
			existing.setTimestamp(LocalDateTime.now());
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Updated Successfully", mapper.map(recipeRepo.save(existing), RecipeDto.class));
		})
		.orElseThrow(() -> new RecipeNotExistsException("This particular recipe with id: " + extId + " not exists yet"));

	}
	
	@Transactional
	public ResponseDto delete(String extId) {
		int status = recipeRepo.deleteByExtIdAndUser(extId, userService.getLoggedInUserDetails());
		if(status == 1) 
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Deleted Successfully", "[]");
		throw new RecipeNotExistsException("This particular recipe with id: " + extId + " not exists yet");
	}
	
	public ResponseDto get(String extId) {
		return recipeRepo.findByExtIdAndUser(extId, userService.getLoggedInUserDetails()).map(recipe->{
			RecipeDto dto = mapper.map(recipe, RecipeDto.class);
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Recipe Found", dto);
		})
		.orElseThrow(()->new RecipeNotExistsException("This particular recipe with id: " + extId + " not exists for this user"));
	}
	
	public ResponseDto getAll() {
		List<Recipe> recipes = recipeRepo.findByUser(userService.getLoggedInUserDetails());
		if(recipes.isEmpty())
			throw new RecipeNotExistsException("User doesn't have any recipe");
		
		List<RecipeDto> dtos = mapper.map(recipes, new TypeToken<List<RecipeDto>>() {}.getType());
	
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Recipes Found", dtos);
	}

}
