package com.spring.projects.app.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.RecipeDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.entites.Favourites;
import com.spring.projects.app.entites.Recipe;
import com.spring.projects.app.exception.RecipeNotExistsException;
import com.spring.projects.app.exception.RecordNotFoundException;
import com.spring.projects.app.repositories.FavouritesRepository;
import com.spring.projects.app.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavouritesService {
	
	private final RecipeRepository recipeRepo;
	private final FavouritesRepository favouriteRepo;
	
	private final UserService userService;
	
	private final ModelMapper mapper;
	
	public ResponseDto addToFavourites(Long recipeId) {
		return recipeRepo.findById(recipeId).map(recipe->{
			Favourites favourite = new Favourites();
			favourite.setRecipe(recipe);
			favourite.setUser(userService.getLoggedInUserDetails());
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Added to favourites", favouriteRepo.save(favourite));
		})
		.orElseThrow(()->new RecipeNotExistsException("This particular recipe with id: " + recipeId + " not exists"));
	}

	public ResponseDto getFavourites() {
		List<Favourites> favouriteRecipes = favouriteRepo.findByUser(userService.getLoggedInUserDetails());
		if(favouriteRecipes.isEmpty())
			throw new RecordNotFoundException("No favourite recipe found");
		
		List<Recipe> recipes = favouriteRecipes.stream()
				.map(Favourites::getRecipe)
				.toList();
		
		List<RecipeDto> mappedRecipes = mapper.map(recipes, new TypeToken<List<RecipeDto>>() {}.getType());
		
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Favourites found", mappedRecipes);
	}

}
