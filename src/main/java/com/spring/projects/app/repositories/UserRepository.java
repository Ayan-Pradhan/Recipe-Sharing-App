package com.spring.projects.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{


}
