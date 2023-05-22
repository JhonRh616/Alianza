package com.alianza.clients.service;

import java.util.List;

import com.alianza.clients.dto.ClientDto;
import com.alianza.clients.exception.AlianzaException;

public interface IClientService {
	
	public List<ClientDto> getAllClients() throws AlianzaException;
	public List<ClientDto> consultByFilters(String sharedKey, String bussinessId, String email, String phone, String dateAdded) throws AlianzaException;
	public ClientDto createClient(ClientDto client) throws AlianzaException;
	
}
