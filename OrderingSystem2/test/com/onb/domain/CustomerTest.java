package com.onb.domain;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	private Customer customer;
	
	@Before
	public void init(){
		customer = new Customer(1,"Ace Opaon");
	}
	
	@Test
	public void testCustomer() {
		Assert.assertEquals(customer.getName(), "Ace Opaon");
		Assert.assertEquals(customer.getId(), 1);
	}
	
	@Test
	public void testAddUnpaidOrder() {
		Order order = new Order(1);
		Product product = new Product(1);
		product.setPrice(new BigDecimal("2500"));
		order.add(new OrderItem(product, 10));
		Assert.assertTrue(customer.getUnpaidOrders().isEmpty());
		
		customer.addUnpaidOrder(order);
		Assert.assertFalse(customer.getUnpaidOrders().isEmpty());
		Set<Order> mock = new LinkedHashSet<Order>();
		mock.add(order);
		Assert.assertEquals(mock, customer.getUnpaidOrders());
		Assert.assertTrue(customer.getUnpaidOrders().contains(order));
		
		product = new Product(2);
		product.setPrice(new BigDecimal("2500"));
		order.add(new OrderItem(product, 10));
		customer.addUnpaidOrder(order);
		Assert.assertEquals(1, customer.getUnpaidOrders().size());
		Assert.assertEquals(new BigDecimal("50000.00"), customer.getTotalUnpaidAmount());
	}


	@Test
	public void testPayOrder() {
		Order order = new Order(1);
		Product product = new Product(1);
		product.setPrice(new BigDecimal("2500"));
		order.add(new OrderItem(product, 10));
		customer.addUnpaidOrder(order);
		Assert.assertTrue(order.isPaid());
		
		customer.payOrder(order);
		Assert.assertFalse(order.isPaid());
		Assert.assertEquals(new BigDecimal("25000.00"), customer.getTotalPaidAmount());
	}

}
