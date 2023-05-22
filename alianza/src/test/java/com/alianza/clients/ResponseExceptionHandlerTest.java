package com.alianza.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alianza.clients.controller.ResponseExceptionHandler;
import com.alianza.clients.dto.ResponseModelDto;
import com.alianza.clients.enums.BussinessErrorEnum;
import com.alianza.clients.exception.AlianzaException;
import com.alianza.clients.repository.IClientRepository;

import static org.junit.jupiter.api.Assertions.*;

class ResponseExceptionHandlerTest extends AlianzaAppApplicationTest {
	
	@Autowired
	private ResponseExceptionHandler responseExceptionHandler;
	
	@MockBean
	private IClientRepository clientRepository;
	
	@Test
	void handleAlianzaExceptionTest() {
		
		AlianzaException alianzaExceptionMock = new AlianzaException(BussinessErrorEnum.PARAMETER_REQUIERED);
		
		ResponseEntity<ResponseModelDto<Void>> responseMocked = responseExceptionHandler.handleAlianzaException(alianzaExceptionMock);
		
		assertNotNull(responseMocked);
		assertNotNull(responseMocked.getBody());
		assertNotNull(responseMocked.getBody().getResponseHeader());
		assertEquals(HttpStatus.BAD_REQUEST, responseMocked.getStatusCode());
	}
	
	@Test
	void handleAlianzaExceptionWithTwoParametersTest() {
		
		Exception exceptionMock = new Exception(BussinessErrorEnum.GENERAL_ERROR.getValue());
		AlianzaException alianzaExceptionMock = new AlianzaException(BussinessErrorEnum.PARAMETER_REQUIERED, exceptionMock);
		
		ResponseEntity<ResponseModelDto<Void>> responseMocked = responseExceptionHandler.handleAlianzaException(alianzaExceptionMock);
		
		assertNotNull(responseMocked);
		assertNotNull(responseMocked.getBody());
		assertNotNull(responseMocked.getBody().getResponseHeader());
		assertEquals(HttpStatus.BAD_REQUEST, responseMocked.getStatusCode());
	}
	
	@Test
	void handleExceptionTest() {
		
		Exception exceptionMock = new Exception(BussinessErrorEnum.GENERAL_ERROR.getValue());
		
		ResponseEntity<ResponseModelDto<Void>> responseMocked = responseExceptionHandler.handleException(exceptionMock);
		
		assertNotNull(responseMocked);
		assertNotNull(responseMocked.getBody());
		assertNotNull(responseMocked.getBody().getResponseHeader());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseMocked.getStatusCode());
	}
	
}

