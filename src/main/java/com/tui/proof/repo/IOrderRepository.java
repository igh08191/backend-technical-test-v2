package com.tui.proof.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tui.proof.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, String> {

	List<Order> findByDeliveryAddress_Postcode(final String postcode);
	List<Order> findByClient_FirstName(final String firstName);
}
