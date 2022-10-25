package com.jbk.politics.Exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PoliticiansDetailNotFoundException.class)
	public ResponseEntity<HashMap<String, Object>> resourceNotFound(PoliticiansDetailNotFoundException exception){
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + new Date());
		hm.put("message", exception.getMessage());
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(AdminDetailNotFoundException.class)
	public ResponseEntity<HashMap<String, Object>> resourceNotFound(AdminDetailNotFoundException exception){
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + new Date());
		hm.put("message", exception.getMessage());
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.BAD_GATEWAY);
	}
	
	
	@ExceptionHandler(OTPWrongEnteredException.class)
	public ResponseEntity<HashMap<String, Object>> otpWrongEnteredException(OTPWrongEnteredException exception){
		
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Time", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		hm.put("message", exception.getMessage());
		
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.BAD_GATEWAY);
	}
	
}
