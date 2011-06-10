package com.onb.orderingsystem.domain;



import org.junit.Assert;
import org.junit.Test;

import com.onb.orderingsystem.domain.InventoryItem;
import com.onb.orderingsystem.domain.Product;
public class InventoryItemTest {

	@Test
	public void testInventoryItem() {
		Product product = new Product("2");
		InventoryItem item = new InventoryItem(product,10);
		Assert.assertEquals(item, new InventoryItem(new Product("2"), 10));
		Assert.assertEquals(item.getProduct(), product);
		Assert.assertEquals(item.getQuantity(), 10);
	}

	@Test
	public void testUpdateQuantity() {
		Product product = new Product("2");
		InventoryItem item = new InventoryItem(product,10);
		item.updateQuantity(3);
		Assert.assertEquals(item.getQuantity(), 7);
	}

}
