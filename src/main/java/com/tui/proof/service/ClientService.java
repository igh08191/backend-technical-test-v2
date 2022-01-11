package com.tui.proof.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tui.proof.model.Client;
import com.tui.proof.repo.IClientRepository;

@Service
public class ClientService {
	@Autowired
	private IClientRepository clientRepository;
	
	public Client createClient(Client client){
		return clientRepository.save(client);
	}
	
	public Client updateClient(Client client){
		return clientRepository.save(client);
	}
	
	public List<Client> findClients(){
		return clientRepository.findAll();
	}

	public Optional<Client> findClient(Integer idClient) {
		return clientRepository.findById(idClient);
	}
	
	public List<Client> findClientByName(String name){
		return clientRepository.findByFirstNameContaining(name);
	}
}
