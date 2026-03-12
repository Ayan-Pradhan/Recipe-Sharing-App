package com.spring.projects.app.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class User {

	@Id
	private String email;
	private String password;
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Recipe> recipes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Favourites> favourites;
}
