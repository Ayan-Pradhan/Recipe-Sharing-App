package com.spring.projects.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.Recipe;
import com.spring.projects.app.entites.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByRecipe(Recipe recipe);
}
