package com.onb.orderingsystem.domain;


 
import java.math.BigDecimal;


import org.junit.*;

import com.onb.orderingsystem.domain.Product;

public class ProductTest {
	private Product product;
	
	@Before
	public void initialize(){
		product = new Product(1);
	}
	
	@Test
	public void testProduct() {
		Assert.assertEquals(product, new Product(1));
	}

	@Test
	public void testSetName() {
		product.setName("My Product");
		Assert.assertEquals("My Product", product.getName());
	}

	@Test
	public void testSetPrice() {
		product.setPrice(new BigDecimal("12.25"));
		Assert.assertEquals(product.getPrice(), new BigDecimal("12.25"));
	}

}
