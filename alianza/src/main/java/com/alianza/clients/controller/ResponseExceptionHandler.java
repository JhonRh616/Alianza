package com.alianza.clients.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alianza.clients.dto.ResponseHeaderDto;
import com.alianza.clients.dto.ResponseModelDto;
import com.alianza.clients.enums.BussinessErrorEnum;
import com.alianza.clients.exception.AlianzaException;
import com.alianza.clients.util.Logger;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger log = Logger.getLogger(ResponseExceptionHandler.class);
	
	@ExceptionHandler(value = AlianzaException.class)
	public @ResponseBody
	ResponseEntity<ResponseModelDto<Void>> handleAlianzaException(AlianzaException e){
		log.error("Message: "+ e.getMessage() + "\nTrace: "+e.getTrace());
		
		return new ResponseEntity<>(new ResponseModelDto<>(new ResponseHeaderDto(e.getCode(), e.getMessage(), e.getTrace()), null), e.getStatus());
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	ResponseEntity<ResponseModelDto<Void>> handleException(Exception e){
		log.error("Message: "+ BussinessErrorEnum.GENERAL_ERROR.getValue() + "\nTrace: "+e.getMessage());
		
		return new ResponseEntity<>(new ResponseModelDto<>(new ResponseHeaderDto(
				BussinessErrorEnum.GENERAL_ERROR.getCode(), BussinessErrorEnum.GENERAL_ERROR.getValue(), e.getMessage()), null), 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
