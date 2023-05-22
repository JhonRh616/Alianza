package com.alianza.clients.dto;

import com.alianza.clients.entity.Client;
import com.alianza.clients.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto {
	
	private String sharedKey;
	private String bussinessId;
	private String email;
	private String phone;
	private String dateAdded;
	
	public ClientDto(Client client) {
		this.sharedKey = client.getSharedKey();
		this.bussinessId = client.getBussinessId();
		this.email = client.getEmail();
		this.phone = client.getPhone();
		this.dateAdded = DateUtil.dateToString(client.getDateAdded());
	}

}
