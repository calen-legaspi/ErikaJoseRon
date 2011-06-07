package com.onb.domain;



import java.util.LinkedHashSet;

import com.onb.domain.Inventory;
import com.onb.domain.InventoryItem;
import com.onb.domain.Product;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {
	
	@Test
	public void testInventory() {
		Inventory inventory = new Inventory();
		Assert.assertEquals(inventory.getInventory(), new LinkedHashSet<InventoryItem>());
	}

	@Test
	public void testAdd() {
		Inventory inventory = new Inventory();
		Product product = new Product(1);
		InventoryItem item = new InventoryItem(product,10);
		inventory.add(item);
		Assert.assertTrue(inventory.getInventory().contains(item));
	}

}
