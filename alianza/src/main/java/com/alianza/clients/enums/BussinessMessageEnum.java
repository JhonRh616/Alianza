package com.alianza.clients.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum BussinessMessageEnum {
	
	SUCCESSFULL_CONSULT("00", "Consulta realizada con éxito", HttpStatus.OK),
	SUCCESSFULL_CREATE("01", "Creación realizada con éxito", HttpStatus.CREATED);
	
	private String code;
	private String value;
	private HttpStatus httpStatus;

}
