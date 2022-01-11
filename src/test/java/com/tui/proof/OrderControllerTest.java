package com.tui.proof;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tui.proof.model.Address;
import com.tui.proof.model.Order;
import com.tui.proof.repo.IOrderRepository;
import com.tui.proof.service.OrderService;
import com.tui.proof.ws.controller.OrderController;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@Slf4j
public class OrderControllerTest {
	@InjectMocks
	OrderController orderController;

	@InjectMocks
	OrderService orderService = spy(OrderService.class);

	@Spy
	IOrderRepository orderRepository;

	@Test
	public void testCreateOrder() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Order mockOrder = new Order(new Address("Street", "PostCode", "City", "Country"), 10);

		when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

		Order order = new Order(new Address("Street", "PostCode", "City", "Country"), 10);
		ResponseEntity<Order> responseEntity = orderController.createOrder(order);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		// assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}

	@Test
	public void testGetOrders() {
		// given
		Order mockOrder1 = new Order(new Address("Street1", "PostCode1", "City1", "Country1"), 10);
		Order mockOrder2 = new Order(new Address("Street2", "PostCode2", "City2", "Country2"), 20);
		List<Order> mockOrders = new ArrayList<Order>(Arrays.asList(mockOrder1, mockOrder2));

		when(orderRepository.findAll()).thenReturn(mockOrders);

		// when
		ResponseEntity<List<Order>> result = orderController.search();

		// then
		assertThat(result.getBody().size()).isEqualTo(2);

		assertThat(result.getBody().get(0).getPilotes()).isEqualTo(mockOrder1.getPilotes());

		assertThat(result.getBody().get(1).getPilotes()).isEqualTo(mockOrder2.getPilotes());
	}
	
	@Test
	public void testUpdate() {
		// given
		Order mockOrder1 = new Order("1", new Address("Street1", "PostCode1", "City1", "Country1"), 10, 12.0, 
				OffsetDateTime.now(), null);
		Order mockOrder2 = new Order("1", new Address("Street1", "PostCode1", "City1", "Country1"), 15, 12.0, 
				OffsetDateTime.now(), null);

		when(orderRepository.findById("1")).thenReturn(Optional.of(mockOrder1));
		when(orderRepository.save(any(Order.class))).thenReturn(mockOrder2);
		
		// when
		ResponseEntity<Order> result = orderController.updateOrder(mockOrder2);

		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testUpdateMoreThan5Minutes() {
		// given
		Order mockOrder1 = new Order("1", new Address("Street1", "PostCode1", "City1", "Country1"), 10, 12.0, 
				OffsetDateTime.now().minusMinutes(10), null);
		Order mockOrder2 = new Order("1", new Address("Street1", "PostCode1", "City1", "Country1"), 15, 12.0, 
				OffsetDateTime.now(), null);

		when(orderRepository.findById("1")).thenReturn(Optional.of(mockOrder1));
		
		// when
		ResponseEntity<Order> result = orderController.updateOrder(mockOrder2);
		
		// then
		assertThat(result.getStatusCodeValue()).isEqualTo(404);
	}
}
