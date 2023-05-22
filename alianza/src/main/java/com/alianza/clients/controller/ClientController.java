package com.alianza.clients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.clients.dto.ClientDto;
import com.alianza.clients.dto.ResponseHeaderDto;
import com.alianza.clients.dto.ResponseModelDto;
import com.alianza.clients.enums.BussinessMessageEnum;
import com.alianza.clients.exception.AlianzaException;
import com.alianza.clients.service.IClientService;
import com.alianza.clients.util.Constants;
import com.alianza.clients.util.Logger;

@RestController
@CrossOrigin("*")
@RequestMapping(value = Constants.V1 + Constants.CLIENTS_ENDPOINT)
public class ClientController {
	
	private static final Logger logger = Logger.getLogger(ClientController.class);
		
	@Autowired
	private IClientService clientService;
	
	@GetMapping(path = Constants.ALL_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModelDto<List<ClientDto>>> consultAllClients() throws AlianzaException {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia consulta de todos los clientes en controlador");
		
		return new ResponseEntity<>(new ResponseModelDto<>(
				new ResponseHeaderDto(BussinessMessageEnum.SUCCESSFULL_CONSULT), clientService.getAllClients()), HttpStatus.OK);
	}
	
	@GetMapping(path = Constants.CONSULT_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModelDto<List<ClientDto>>> consultByFilters(
			@RequestParam(value = "sharedKey", required = false) String sharedKey,
			@RequestParam(value = "bussinessId", required = false) String bussinessId,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "dateAdded", required = false) String dateAdded
			) throws AlianzaException {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia consulta de clientes por filtros en controlador");
		
		return new ResponseEntity<>(new ResponseModelDto<>(
				new ResponseHeaderDto(BussinessMessageEnum.SUCCESSFULL_CONSULT), clientService.consultByFilters(
						sharedKey, bussinessId, email, phone, dateAdded)), HttpStatus.OK);
	}
	
	@PostMapping(path = Constants.CREATE_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModelDto<ClientDto>> createClient(@RequestBody ClientDto client) throws AlianzaException {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia creación de cliente en controlador");
		
		return new ResponseEntity<>(new ResponseModelDto<>(
				new ResponseHeaderDto(BussinessMessageEnum.SUCCESSFULL_CREATE), clientService.createClient(client)), HttpStatus.CREATED);
	}

}
