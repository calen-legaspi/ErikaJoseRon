package com.onb.orderingsystem.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


import com.onb.orderingsystem.bean.CustomerObject;
import com.onb.orderingsystem.dao.CustomerDAO;
import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.domain.Customer;
import com.onb.orderingsystem.domain.Order;


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
	
	private Collection<CustomerObject> toCustomerObjectBean(Collection<Customer> customers){
		Collection<CustomerObject> customerList = new HashSet<CustomerObject>();
		OrderServiceManager order = new OrderServiceManager();
		for (Customer customer : customers){
			CustomerObject customerBean = new CustomerObject();
			customerBean.setId(customer.getId());
			customerBean.setName(customer.getName());
			customerBean.setCreditLimit(customer.getCreditLimit());
			customerBean.setPaidAmt(customer.getTotalPaidAmount());
			customerBean.setUnpaidAmt(customer.getTotalUnpaidAmount());
			customerBean.setOrders(order.findCustomerOrders(customer.getId()));
			customerList.add(customerBean);
		}
		return customerList;
	}
	
	public Collection<CustomerObject> getCustomersWithValidCreditLimit(){
		CustomerDAO customer = dao.getCustomerDAO();
		OrderServiceManager orderService = new OrderServiceManager();
		Collection<Customer> customers = new HashSet<Customer>();
		try {
			for(Customer person: customer.listAllCustomer()){
				for(Order order:orderService.findOrders(person.getId())){
					person.addUnpaidOrder(order);
				}
				if(person.getTotalUnpaidAmount().compareTo(person.getCreditLimit())==-1){
					customers.add(person);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toCustomerObjectBean(customers);
	}
	
	public Collection<CustomerObject> getCustomerList(){
		CustomerDAO customer = dao.getCustomerDAO();
		Collection<Customer> customers = new HashSet<Customer>();
		try {
			customers = customer.listAllCustomer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toCustomerObjectBean(customers);
	}
	
	public Collection<CustomerObject> getCustomersWithUnpaidOrder(){
		CustomerDAO customer = dao.getCustomerDAO();
		OrderServiceManager orderService = new OrderServiceManager();
		Collection<Customer> customers = new HashSet<Customer>();
		try {
			for(Customer person: customer.listAllCustomer()){
				for(Order order:orderService.findOrders(person.getId())){
					person.addUnpaidOrder(order);
				}
				if(!person.getUnpaidOrders().isEmpty()){
					customers.add(person);
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toCustomerObjectBean(customers);
	}
	
}


