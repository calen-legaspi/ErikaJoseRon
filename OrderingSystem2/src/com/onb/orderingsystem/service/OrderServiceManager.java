package com.onb.orderingsystem.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import com.onb.orderingsystem.bean.OrderObject;
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
	
	public Collection<OrderObject> findCustomerOrders(int customerId){
		OrderDAO orderDao = dao.getOrderDAO();
		Collection<OrderObject> orders = new HashSet<OrderObject>(); 
		try {
			for(Order order: orderDao.findAllOrderByCustomer(customerId)){
				OrderObject orderBean = new OrderObject();
				orderBean.setCustomerID(customerId);
				orderBean.setDate(order.getDate());
				orderBean.setOrders(order.getOrders());
				orderBean.setTotal(order.getTotal());
				orderBean.setStatus(order.getOrderStatus());
				orderBean.setId(order.getId());
				orders.add(orderBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

}

