package com.spring.projects.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.projects.app.constants.ResponseStatusCode;
import com.spring.projects.app.dtos.ErrorResponseDto;

@RestControllerAdvice
public class RecipeAppExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ErrorResponseDto(ResponseStatusCode.FAILED, ex.getMessage(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponseDto(ResponseStatusCode.FAILED, ex.getMessage(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(RecipeNotExistsException.class)
	public final ResponseEntity<ErrorResponseDto> handleRecipeNotExistsException(RecipeNotExistsException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponseDto(ResponseStatusCode.FAILED, ex.getMessage(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponseDto> handleRecordNotFoundException(RecordNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponseDto(ResponseStatusCode.FAILED, ex.getMessage(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseDto> handleOtherExceptions(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponseDto(ResponseStatusCode.FAILED, ex.getMessage(), LocalDateTime.now()));
	}
}
