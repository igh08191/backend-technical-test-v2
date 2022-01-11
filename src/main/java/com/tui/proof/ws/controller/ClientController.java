package com.tui.proof.ws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.model.Client;
import com.tui.proof.service.ClientService;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/tui/FooController/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Timed("com.tui.FooController.client")
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody @Valid Client client) {
		log.info("Client controller - create Client");
		
		return new ResponseEntity<>(this.clientService.createClient(client), HttpStatus.CREATED);
	}

	@Timed("com.tui.FooController.search")
	@GetMapping
	public ResponseEntity<List<Client>> search() {
		log.info("Client controller - search");
		return ResponseEntity.ok(this.clientService.findClients());
	}

	@Timed("com.tui.FooController.searchById")
	@GetMapping("/{id}")
	public ResponseEntity<Client> searchById(@PathVariable(name="id") Integer idClient) {
		log.info("Client controller - searchById");
		return this.clientService.findClient(idClient).map(ResponseEntity::ok).
				orElseGet(()->ResponseEntity.notFound().build());
	}

	@Timed("com.tui.FooController.searchByName")
	@GetMapping("/firstname/{name}")
	public ResponseEntity<List<Client>> searchByName(@PathVariable(name="name") String firstName) {
		log.info("Client controller - searchByName");
		List<Client> listClients = this.clientService.findClientByName(firstName);
		
		if( listClients!= null && !listClients.isEmpty()){
			return ResponseEntity.ok(listClients);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Timed("com.tui.FooController.update")
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client) {
		log.info("Client controller - update");
		
		return this.clientService.findClient(client.getIdClient()).
				map( o->ResponseEntity.ok(this.clientService.updateClient(client))).
				orElseGet(()->ResponseEntity.notFound().build());
	}
}
