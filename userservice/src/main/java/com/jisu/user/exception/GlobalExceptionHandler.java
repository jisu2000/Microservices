package com.jisu.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jisu.user.service.playload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String msg=ex.getMessage();
		ApiResponce res=new ApiResponce();
		res.setMessage(msg);
		res.setStatus(HttpStatus.NOT_FOUND);
		res.setSuccess(true);
		
		return new ResponseEntity<ApiResponce>(res,HttpStatus.NOT_FOUND);
		
	}
}
