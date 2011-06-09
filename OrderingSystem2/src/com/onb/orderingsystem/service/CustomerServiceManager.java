package com.onb.orderingsystem.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.onb.orderingsystem.dao.CustomerDAO;
import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.domain.Customer;

public class CustomerServiceManager {
	private DAOFactory dao;
	
	public CustomerServiceManager(){
		try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Set<Customer> getCustomersWithValidCreditLimit(){
		CustomerDAO customer = dao.getCustomerDAO();
		Set<Customer> customers = new HashSet<Customer>();
		try {
			for(Customer person: customer.listAllCustomer()){
				if(person.getTotalUnpaidAmount().compareTo(person.getCreditLimit())==-1){
					customers.add(person);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	
	public Set<Customer> getCustomerList(){
		CustomerDAO customer = dao.getCustomerDAO();
		return customer.listAllCustomer();
	}
	
	public Set<Customer> getCustomersWithUnpaidOrder(){
		CustomerDAO customer = dao.getCustomerDAO();
		Set<Customer> customers = new HashSet<Customer>();
		try {
			for(Customer person: customer.listAllCustomer()){
				if(!(person.getUnpaidOrders().isEmpty())){
					customers.add(person);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	
}
