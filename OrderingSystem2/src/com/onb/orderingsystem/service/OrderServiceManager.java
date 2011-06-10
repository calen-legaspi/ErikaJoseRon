package com.onb.orderingsystem.service;

import java.sql.SQLException;

import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.dao.OrderDAO;
import com.onb.orderingsystem.domain.Order;

public class OrderServiceManager {
	private DAOFactory dao;
	
	public OrderServiceManager(){
		try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Order findOrderByID(int orderID){
		OrderDAO order = dao.getOrderDAO();
		Order myOrder = null;
		try {
			myOrder = order.findOrderByID(orderID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myOrder;
	}
	
	public void insertOrder(Order newOrder){
		OrderDAO order = dao.getOrderDAO();
		try {
			order.insertOrder(newOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
