package com.spring.projects.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projects.app.entites.Follow;

// work in progress

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{

}
