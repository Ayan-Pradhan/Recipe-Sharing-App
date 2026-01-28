package com.spring.projects.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.Favourites;
import com.spring.projects.app.entites.User;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long>{
	List<Favourites> findByUser(User user);
}
