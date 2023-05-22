package com.alianza.clients.dto;

import com.alianza.clients.enums.BussinessMessageEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHeaderDto {
	
	private String code;
	private String message;
	private String trace;
	
	public ResponseHeaderDto(BussinessMessageEnum message) {
		this.code = message.getCode();
		this.message = message.getValue();
	}

}
