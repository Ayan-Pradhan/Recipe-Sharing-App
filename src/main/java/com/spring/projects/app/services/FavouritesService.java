package com.spring.projects.app.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.FavouritesDto;
import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.entites.Favourites;
import com.spring.projects.app.exception.RecipeNotExistsException;
import com.spring.projects.app.exception.RecordNotFoundException;
import com.spring.projects.app.repositories.FavouritesRepository;
import com.spring.projects.app.repositories.RecipeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavouritesService {
	
	private final RecipeRepository recipeRepo;
	private final FavouritesRepository favouriteRepo;
	
	private final UserService userService;
	public final IdGenerationService idGenerationService;
	
	private final ModelMapper mapper;
	
	@Transactional
	public ResponseDto add(String recipeId) {
		return recipeRepo.findByExtId(recipeId).map(recipe->{
			Favourites favourite = new Favourites();
			favourite.setExtId(idGenerationService.generate());
			favourite.setRecipe(recipe);
			favourite.setUser(userService.getLoggedInUserDetails());
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Added to favourites", mapper.map(favouriteRepo.save(favourite), FavouritesDto.class));
		})
		.orElseThrow(()->new RecipeNotExistsException("This particular recipe with id: " + recipeId + " not exists"));
	}
	
	@Transactional
	public ResponseDto delete(String extId) {
		int status = favouriteRepo.deleteByExtIdAndUser(extId, userService.getLoggedInUserDetails());
		if(status == 1)
			return new ResponseDto(ResponseStatusCode.SUCCESS, "Removed from favourites", "[]");
		throw new RecordNotFoundException("No record found with the id "+extId);
	}

	
	public ResponseDto getAll() {
		List<Favourites> favouriteRecipes = favouriteRepo.findByUser(userService.getLoggedInUserDetails());
		if(favouriteRecipes.isEmpty())
			throw new RecordNotFoundException("No favourite recipes found for the user");
		
		List<FavouritesDto> mappedFavourites = mapper.map(favouriteRecipes, new TypeToken<List<FavouritesDto>>() {}.getType());
		
		return new ResponseDto(ResponseStatusCode.SUCCESS, "Favourites found", mappedFavourites);
	}

}
