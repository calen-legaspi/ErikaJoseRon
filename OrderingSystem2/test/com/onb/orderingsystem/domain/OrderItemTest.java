package com.onb.orderingsystem.domain;



import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.domain.Product;
public class OrderItemTest {

	@Test
	public void testOrderItem() {
		Product product = new Product("1");
		OrderItem item = new OrderItem(1,product, 10);
		Assert.assertEquals(item.getProduct(), product);
		Assert.assertTrue(item.getQuantity()==10);
	}

	@Test
	public void testComputeAmount() {
		Product product = new Product("1");
		product.setPrice(new BigDecimal("3.5"));
		OrderItem item = new OrderItem(1, product, 10);
		Assert.assertEquals(item.computeAmount().setScale(2), new BigDecimal("35").setScale(2) );
		
	}

}
