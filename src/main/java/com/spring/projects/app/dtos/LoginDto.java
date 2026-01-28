package com.spring.projects.app.dtos;

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
public class LoginDto {
	
	@NotBlank(message="Username can't be blank")
	@Email(message="Invalid username")
	private String email;
	
	@NotBlank(message="Password can't be blank")
	@Size(min=8, message="Password must be 8 chars long")
	private String password;

}
