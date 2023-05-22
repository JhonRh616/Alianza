package com.alianza.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;

import com.alianza.clients.controller.ClientController;
import com.alianza.clients.dto.ClientDto;
import com.alianza.clients.dto.ResponseModelDto;
import com.alianza.clients.entity.Client;
import com.alianza.clients.enums.BussinessErrorEnum;
import com.alianza.clients.exception.AlianzaException;
import com.alianza.clients.repository.IClientRepository;
import com.alianza.clients.util.DateUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class ClientControllerTest extends AlianzaAppApplicationTest {
	
	@Autowired
	private ClientController clientController;
	
	@MockBean
	private IClientRepository clientRepository;
	
	private List<Client> clientList;
	private List<ClientDto> clientListDto;
	private String CODE_00 = "00";
	private String CODE_01 = "01";
	private String CODE_02 = "02";

	@Test
	void getAllClientsTest() throws AlianzaException {
		
		when(clientRepository.findAll()).thenReturn(getClientList());
		
		ResponseEntity<ResponseModelDto<List<ClientDto>>> responseMock = clientController.consultAllClients();
		
		assertNotNull(responseMock);
		assertNotNull(responseMock.getBody());
		assertNotNull(responseMock.getBody().getResponseHeader());
		assertNotNull(responseMock.getBody().getResponseBody());
		assertEquals(CODE_00, responseMock.getBody().getResponseHeader().getCode());
		assertEquals(getClientListDto(), responseMock.getBody().getResponseBody());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void consultByFiltersTest() throws AlianzaException {
		
		when(clientRepository.findAll(any(Example.class))).thenReturn(getClientList());
		
		ResponseEntity<ResponseModelDto<List<ClientDto>>> responseMock = clientController.consultByFilters(
				"jgutierrez", null, null, null, null);
		
		assertNotNull(responseMock);
		assertNotNull(responseMock.getBody());
		assertNotNull(responseMock.getBody().getResponseHeader());
		assertNotNull(responseMock.getBody().getResponseBody());
		assertEquals(CODE_00, responseMock.getBody().getResponseHeader().getCode());
		assertEquals(getClientListDto(), responseMock.getBody().getResponseBody());
				
		responseMock = clientController.consultByFilters(
				null, "Juliana Gutierrez", null, null, null);
		assertNotNull(responseMock);
		
		responseMock = clientController.consultByFilters(
				null, null, "mmartinez@gmail.com", null, null);
		assertNotNull(responseMock);
		
		responseMock = clientController.consultByFilters(
				null, null, null, "321987543", null);
		assertNotNull(responseMock);
		
		responseMock = clientController.consultByFilters(
				null, null, null, null, "20/05/2019");
		assertNotNull(responseMock);
	}
	
	@Test
	void consultByFiltersWithParseExceptionTest() throws AlianzaException {
		try {
			clientController.consultByFilters(
					"jgutierrez", "Juliana Gutierrez", "mmartinez@gmail.com", "321987543", "20-05-2019");
		}catch (AlianzaException e) {
			assertEquals(CODE_02, e.getCode());
			assertEquals(BussinessErrorEnum.DATE_PARSE_EXCEPTION.getValue(), e.getMessage());
			assertEquals(BussinessErrorEnum.DATE_PARSE_EXCEPTION.getHttpStatus(), e.getStatus());
		}		
	}
	
	
	@Test
	void consultByFiltersWithoutParametersTest() throws AlianzaException {
		try {
			clientController.consultByFilters(
					null, null, null, null, null);
		}catch (AlianzaException e) {
			assertEquals(CODE_02, e.getCode());
			assertEquals(BussinessErrorEnum.PARAMETER_REQUIERED.getValue(), e.getMessage());
			assertEquals(BussinessErrorEnum.PARAMETER_REQUIERED.getHttpStatus(), e.getStatus());
		}		
	}
	
	@Test
	void createClientTest() throws AlianzaException {
		
		when(clientRepository.save(any())).thenReturn(getClientList().get(0));
		
		ResponseEntity<ResponseModelDto<ClientDto>> responseMock = clientController.createClient(getClientListDto().get(0));
		
		assertNotNull(responseMock);
		assertNotNull(responseMock.getBody());
		assertNotNull(responseMock.getBody().getResponseHeader());
		assertNotNull(responseMock.getBody().getResponseBody());
		assertEquals(CODE_01, responseMock.getBody().getResponseHeader().getCode());
		assertEquals(getClientListDto().get(0), responseMock.getBody().getResponseBody());
	}
	
	private List<Client> getClientList() throws AlianzaException {
		
		clientList = new ArrayList<>();
		Client client1 = new Client(10, "jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com", "321987543", DateUtil.stringToDate("20/05/2019"));
		Client client2 = new Client(20, "mmartinez", "Manuel Martinez", "mmartinez@gmail.com", "321987543", DateUtil.stringToDate("20/05/2019"));
		
		clientList.add(client1);
		clientList.add(client2);
		
		return clientList;
		
	}
	
	private List<ClientDto> getClientListDto(){
		
		clientListDto = new ArrayList<>();
		ClientDto client1 = new ClientDto("jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com", "321987543", "20/05/2019");
		ClientDto client2 = new ClientDto("mmartinez", "Manuel Martinez", "mmartinez@gmail.com", "321987543", "20/05/2019");
		
		clientListDto.add(client1);
		clientListDto.add(client2);
		
		return clientListDto;
		
	}
	
}

