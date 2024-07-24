package com.project.k6.config;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.k6.util.CustomJWTException;

//예외 처리를 위한 컨트롤러 어드바이스 클래스.
public class CustomControllerAdvice {
	
	@ExceptionHandler(CustomJWTException.class)
	protected ResponseEntity<?> handleJWTException(CustomJWTException e){
		
		String msg = e.getMessage();
		
		return ResponseEntity.ok().body(Map.of("error", msg));
	}
}
