package com.spring.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/favourite/all")
	public ResponseEntity<ResponseDto> getFavourites(){
		return ResponseEntity.ok(favourites.getAll());
	}
	
	@PostMapping("/favourite/recipe/{recipeId}")
	public ResponseEntity<ResponseDto> addToFavourite(@PathVariable String recipeId) {
		return ResponseEntity.ok(favourites.add(recipeId));
	}
	
	@DeleteMapping("/favourite/{id}")
	public ResponseEntity<ResponseDto> removeFromFavourite(@PathVariable String id){
		return ResponseEntity.ok(favourites.delete(id));
	}
	
	

}
