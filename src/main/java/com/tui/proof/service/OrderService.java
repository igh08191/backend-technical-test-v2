package com.tui.proof.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tui.proof.model.Order;
import com.tui.proof.repo.IOrderRepository;

@Service
public class OrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	public Order createOrder(Order order){
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Order order){
		return orderRepository.save(order);
	}
//	public Optional<Order> updateOrder(Order order){
//		return Optional.of(this.findOrder(order.getNumber()).
//		filter(o -> o.getCreated().isAfter(OffsetDateTime.now().minusMinutes(5))).
//		map( or -> orderRepository.save(order)).get());
//	}
	
	public List<Order> findOrders(){
		return orderRepository.findAll();
	}

	public Optional<Order> findOrder(String number) {
		return orderRepository.findById(number);
	}
}
