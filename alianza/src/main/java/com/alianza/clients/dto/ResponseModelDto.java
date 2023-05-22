package com.alianza.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseModelDto<T> {
	
	private ResponseHeaderDto responseHeader;
	private T responseBody;
	
}
