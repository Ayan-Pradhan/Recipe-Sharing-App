package com.spring.projects.app.entites;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// work in progress

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime timeStamp;
	
	@JsonIgnore
	@ManyToOne
	private User following;
	
	@JsonIgnore
	@ManyToOne
	private User follower;
	
}
