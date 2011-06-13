package com.onb.orderingsystem.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.onb.orderingsystem.bean.CustomerObject;


public class CustomerServiceTest {
	CustomerServiceManager customer;
	@Before
	public void init(){
		customer = new CustomerServiceManager();
	}
	
	@Test
	public void testGetCustomersWithValidCreditLimit() {
		Collection<CustomerObject> customerList = customer.getCustomersWithValidCreditLimit();
		Assert.assertFalse(customerList.isEmpty());
		for(CustomerObject person: customerList){
			Assert.assertTrue(person.getUnpaidAmt().compareTo(person.getCreditLimit())==-1);
		}
	}

	@Test
	public void testGetCustomerList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomersWithUnpaidOrder() {
		fail("Not yet implemented");
	}

}
