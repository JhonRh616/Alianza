package com.alianza.clients.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alianza.clients.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer>{
	
	public List<Client> getBySharedKey(String sharedKey);
	
}
