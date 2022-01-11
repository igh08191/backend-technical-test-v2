package com.tui.proof.service;

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
	
	public List<Order> findOrders(){
		return orderRepository.findAll();
	}

	public Optional<Order> findOrder(String number) {
		return orderRepository.findById(number);
	}
}
