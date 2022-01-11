package com.tui.proof.ws.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/tui/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Timed("com.tui.order")
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) {
		log.info("Order controller - create Order");
		
		return new ResponseEntity<>(this.orderService.createOrder(order), HttpStatus.CREATED);
	}

	@PreAuthorize("isAuthenticated()")
	@Timed("com.tui.order.search")
	@GetMapping
	public ResponseEntity<List<Order>> search() {
		log.info("Order controller - search");
		return ResponseEntity.ok(this.orderService.findOrders());
	}

	@PreAuthorize("isAuthenticated()")
	@Timed("com.tui.order.searchId")
	@GetMapping("/{id}")
	public ResponseEntity<Order> searchById(@PathVariable(name="id") String idOrder) {
		log.info("Order controller - searchById");
		return this.orderService.findOrder(idOrder).map(ResponseEntity::ok).
				orElseGet(()->ResponseEntity.notFound().build());
	}

	@Timed("com.tui.order.update")
	@PutMapping
	public ResponseEntity<Order> updateOrder(@RequestBody @Valid Order order) {
		log.info("Order controller - update");
		
		return this.orderService.findOrder(order.getNumber()).
				filter(o->o.getCreated().isBefore(OffsetDateTime.now().minusMinutes(5))).
				map( o->ResponseEntity.ok(this.orderService.updateOrder(order))).
				orElseGet(()->ResponseEntity.notFound().build());
	}
}
