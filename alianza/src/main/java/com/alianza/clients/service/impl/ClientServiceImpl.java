package com.alianza.clients.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alianza.clients.dto.ClientDto;
import com.alianza.clients.entity.Client;
import com.alianza.clients.enums.BussinessErrorEnum;
import com.alianza.clients.exception.AlianzaException;
import com.alianza.clients.repository.IClientRepository;
import com.alianza.clients.service.IClientService;
import com.alianza.clients.util.DateUtil;
import com.alianza.clients.util.Logger;

import static java.util.Objects.*;

import java.util.Date;

@Service
public class ClientServiceImpl implements IClientService {
	
	private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Override
	public List<ClientDto> getAllClients() throws AlianzaException {	
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia consulta de todos los clientes");
		
		return clientRepository.findAll().stream().map(ClientDto::new).collect(Collectors.toList());
	}
	
	@Override
	public List<ClientDto> consultByFilters(String sharedKey, String bussinessId, String email, String phone, String dateAdded) throws AlianzaException {	
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia consulta de cliente por filtros");
		
		Date date = null;
		
		if(	isNull(sharedKey) 
				&& isNull(bussinessId) 
				&& isNull(email) 
				&& isNull(phone) 
				&& isNull(dateAdded)) 
			throw new AlianzaException(BussinessErrorEnum.PARAMETER_REQUIERED);
		
		if(nonNull(dateAdded))
			date = DateUtil.stringToDate(dateAdded);
			
		return clientRepository.findAll(Example.of(new Client(null, sharedKey, bussinessId, email, phone, date)))
				.stream().map(ClientDto::new).collect(Collectors.toList());

	}

	@Override
	public ClientDto createClient(ClientDto clientDto) throws AlianzaException {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("["+methodName+"] Inicia creación del cliente");
		
		Client client = new Client(
				null, clientDto.getSharedKey(), 
				clientDto.getBussinessId(), 
				clientDto.getEmail(), 
				clientDto.getPhone(), 
				DateUtil.stringToDate(clientDto.getDateAdded()));
		
		return new ClientDto(clientRepository.save(client));
	}

}
