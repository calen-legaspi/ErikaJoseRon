package com.onb.orderingsystem.service;

import java.sql.SQLException;

import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.dao.InventoryItemDAO;
import com.onb.orderingsystem.domain.InventoryItem;

public class InventoryItemServiceManager {
	private DAOFactory dao;
	
	public InventoryItemServiceManager(){
		try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateQuantity(String sku, int newQuantity){
		InventoryItemDAO inventoryItem = dao.getInventoryItemDAO();
		try {
			inventoryItem.updateProductQuantity(sku, newQuantity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InventoryItem findItemBySKU(String sku){
		InventoryItemDAO inventoryItem = dao.getInventoryItemDAO();
		InventoryItem item = null;
		try {
			item = inventoryItem.findItemBySKU(sku);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
