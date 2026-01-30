package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projects.app.dtos.ResponseDto;
import com.spring.projects.app.services.FavouritesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favourites")
public class FavouritesController {
	
	private final FavouritesService favourites;
	
	@GetMapping("/favourite/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> addFavourite(@PathVariable Long recipeId) {
		return ResponseEntity.ok(favourites.addToFavourites(recipeId));
	}
	
	@GetMapping("/favourite/recipe")
	public ResponseEntity<ResponseDto> favourites(){
		return ResponseEntity.ok(favourites.getFavourites());
	}

}
