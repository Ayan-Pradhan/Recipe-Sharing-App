package com.spring.projects.app.dtos;

import java.time.LocalDateTime;

import com.spring.projects.app.constants.ResponseStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
	private ResponseStatusCode status;
	private String message;
	private LocalDateTime timestamp;
}
