package com.spring.projects.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.Recipe;
import com.spring.projects.app.entites.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	Optional<Recipe> findByExtIdAndUser(String extId, User user);
	List<Recipe> findByUser(User user);
	Optional<Recipe> findByExtId(String extId);
	int deleteByExtIdAndUser(String extId, User user);
}
