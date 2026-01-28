package com.spring.projects.app.dtos;

import com.spring.projects.app.constants.ResponseStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
	private ResponseStatusCode status;
	private String message;
	private Object payload;

}
