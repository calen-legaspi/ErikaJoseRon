package com.onb.orderingsystem.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
	
	public Collection<InventoryItem> ListItemsInStock(){
		InventoryItemDAO  inventoryItem = dao.getInventoryItemDAO();
		Collection<InventoryItem> itemList = new ArrayList<InventoryItem>();
		try {
			for(InventoryItem i: inventoryItem.listAllItem()){
				if(i.getQuantity()>0) itemList.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}

}
