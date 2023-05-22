package com.alianza.clients.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum BussinessErrorEnum {
	
	GENERAL_ERROR("02", "Error general en la aplicación", HttpStatus.INTERNAL_SERVER_ERROR),
	PARAMETER_REQUIERED("02", "Al menos un parámetro es requerido", HttpStatus.BAD_REQUEST),
	DATE_PARSE_EXCEPTION("02", "Error al transformar la fecha", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private String code;
	private String value;
	private HttpStatus httpStatus;

}
