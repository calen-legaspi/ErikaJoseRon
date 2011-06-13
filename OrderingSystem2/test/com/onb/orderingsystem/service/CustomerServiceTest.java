package com.onb.orderingsystem.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.onb.orderingsystem.bean.CustomerObject;
import com.onb.orderingsystem.bean.OrderObject;

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
		OrderObject myOrder = new OrderObject();
		for(CustomerObject person: customerList){
			myOrder.setStatus(0);
			Assert.assertTrue(person.getOrders().contains(myOrder));
		}
	}

}
