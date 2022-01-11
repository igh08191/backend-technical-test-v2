package com.tui.proof;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tui.proof.model.Address;
import com.tui.proof.model.Order;
import com.tui.proof.repo.IOrderRepository;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

	@Autowired
    private IOrderRepository repository;

    @Test
    public void testFindByPostcode() {

        entityManager.persist(new Order(new Address("Maestro Arrieta","41010","Seville","Seville"), 12));
        entityManager.persist(new Order(new Address("Vic","08091","L'Hospitalet","Barcelona"), 1));
        repository.save(new Order(new Address("Arjona","41001","Seville","Seville"), 2));

        List<Order> orders = repository.findByDeliveryAddress_Postcode("41010");
        assertEquals(1, orders.size());

        assertThat(orders).extracting(Order::getPilotes).contains(12);

    }
    
    @Test
    public void testFindByExamplePostcode() {

    	repository.save(new Order(new Address("Maestro Arrieta","41010","Seville","Seville"), 12));
    	repository.save(new Order(new Address("Arjona","41001","Seville","Seville"), 2));
    	repository.save(new Order(new Address("Vic","08091","L'Hospitalet","Barcelona"), 1));
        
    	List<Order> filteredOrders = StreamSupport.stream(repository.findAll().spliterator(), false).
    			filter(order -> order.getDeliveryAddress().getPostcode().startsWith("41")).
    			collect(Collectors.toList());
    	
        assertEquals(2, filteredOrders.size());
    }

    @Test
    public void test_createOrder_updateOrder() {

    	Order order = repository.save(new Order(new Address("Maestro Arrieta","41010","Seville","Seville"), 12));

    	order.setPilotes(2);
    	
    	repository.save(order);
    	
    	Optional<Order> updatedOrder = repository.findById(order.getNumber());
    	
    	assertThat(updatedOrder.isPresent());
    	assertEquals(2, updatedOrder.get().getPilotes());
    }
}
