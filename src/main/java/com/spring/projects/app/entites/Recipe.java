package com.spring.projects.app.entites;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private String cookingTime;
	private String category;
	private List<String> ingredients;
	private String steps;
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "recipe")
	@JsonIgnore
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "recipe")
	@JsonIgnore
	private List<Favourites> favouriteRecipe;
}
