package com.spring.projects.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.Recipe;
import com.spring.projects.app.entites.Review;
import com.spring.projects.app.entites.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByRecipe(Recipe recipe);
	int deleteByExtIdAndUser(String extId, User user);
}
