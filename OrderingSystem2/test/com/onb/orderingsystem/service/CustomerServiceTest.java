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
		Collection<CustomerObject> customerList = customer.getCustomerList();
		Assert.assertFalse(customerList.isEmpty());
	}

	@Test
	public void testGetCustomersWithUnpaidOrder() {
		Collection<CustomerObject> customerList = customer.getCustomersWithUnpaidOrder();
		for(CustomerObject person: customerList){
			
		}
	}

}
