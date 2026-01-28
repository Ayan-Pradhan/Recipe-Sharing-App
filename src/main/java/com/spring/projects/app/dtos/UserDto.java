package com.spring.projects.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank(message="Username can't be blank")
	@Email(message="Invalid username")
	private String email;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Size(min=8, message="Password must be 8 chars long")
	private String password;
	
	@Size(min=4, message="Invalid Role")
	private String role;

}
