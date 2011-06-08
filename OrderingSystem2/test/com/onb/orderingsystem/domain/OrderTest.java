package com.onb.orderingsystem.domain;

import java.math.BigDecimal;
import org.junit.*;

import com.onb.orderingsystem.domain.Order;
import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.domain.Product;

public class OrderTest {

	@Test
	public void testOrder() {
		Order order = new Order(1);
		Assert.assertEquals(order.getId(),1);
	}

	@Test
	public void testAdd() {
		Order order = new Order(1);
		Assert.assertTrue(order.getOrders().isEmpty());
		
		Product product = new Product(1);
		product.setPrice(new BigDecimal("2500"));
		OrderItem orderItem = new OrderItem(product,10);
		order.add(orderItem);
		
		Assert.assertTrue(order.getOrders().contains(orderItem));
		
		order.add(orderItem);
		Assert.assertEquals(order.getOrders().size(),1);
		Assert.assertEquals(new BigDecimal("50000"), order.getTotal());
	}

	@Test
	public void testDelete() {
		Order order = new Order(1);
		Assert.assertTrue(order.getOrders().isEmpty());
		
		Product product = new Product(1);
		product.setPrice(new BigDecimal("2500"));
		OrderItem orderItem = new OrderItem(product,10);
		order.add(orderItem);
		
		order.delete(orderItem);
		Assert.assertFalse(order.getOrders().contains(orderItem));
	}

}
