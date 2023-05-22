package com.alianza.clients.exception;

import org.springframework.http.HttpStatus;

import com.alianza.clients.enums.BussinessErrorEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlianzaException extends Exception {
	
	private static final long serialVersionUID = 1089473901732824342L;
	private final String code;
	private final String message;
	private final String trace;
	private final HttpStatus status;
	
	public AlianzaException(BussinessErrorEnum bussinessErrorEnum, Exception e) {
		this.code = bussinessErrorEnum.getCode();
		this.message = bussinessErrorEnum.getValue();
		this.status = bussinessErrorEnum.getHttpStatus();
		this.trace = e.getMessage();
	}
	
	public AlianzaException(BussinessErrorEnum bussinessErrorEnum) {
		this.code = bussinessErrorEnum.getCode();
		this.message = bussinessErrorEnum.getValue();
		this.status = bussinessErrorEnum.getHttpStatus();
		this.trace = bussinessErrorEnum.getValue();
	}
	

}
