package com.onb.orderingsystem.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import com.onb.orderingsystem.bean.OrderItemObject;
import com.onb.orderingsystem.bean.OrderObject;
import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.dao.OrderDAO;
import com.onb.orderingsystem.domain.Order;
import com.onb.orderingsystem.domain.OrderItem;

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
	
	private OrderObject toOrderObjectBean(Order order){
		OrderObject orderBean = new OrderObject();
		orderBean.setCustomerID(order.getCustomerID());
		orderBean.setDate(order.getDate());
		orderBean.setId(order.getId());
		orderBean.setTotal(order.getTotal());
		orderBean.setStatus(order.getOrderStatus());
		
		Collection<OrderItemObject> items = new HashSet<OrderItemObject>();
		for(OrderItem orderItem: order.getOrders()){
			OrderItemObject item = new OrderItemObject();
			item.setId(orderItem.getID());
			item.setQuantity(orderItem.getQuantity());
			item.setAmount(orderItem.computeAmount());
			
			ProductServiceManager product = new ProductServiceManager();
			item.setProduct(product.findProductBySKU(orderItem.getProduct().getSkuNumber()));
			items.add(item);
		}
		orderBean.setOrders(items);
		return orderBean;
	}
	
	public OrderObject findOrderByID(int orderID){
		OrderDAO order = dao.getOrderDAO();
		Order myOrder = null;
		try {
			myOrder = order.findOrderByID(orderID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(myOrder==null){
			throw new NullPointerException();
		}
		return toOrderObjectBean(myOrder);
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
				orders.add(toOrderObjectBean(order));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public Collection<Order> findOrders(int customerId){
		OrderDAO orderDao = dao.getOrderDAO();
		Collection<Order> orders = new HashSet<Order>(); 
		try {
			for(Order order: orderDao.findAllOrderByCustomer(customerId)){
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

}

