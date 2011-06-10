package com.onb.orderingsystem.service;

import java.sql.SQLException;

import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.dao.ProductDAO;
import com.onb.orderingsystem.domain.Product;

public class ProductServiceManager {
	private DAOFactory dao;
	
	public ProductServiceManager(){
		try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Product findProductBySKU(String sku){
		ProductDAO product = dao.getProductDAO();
		Product myProduct = null;
		try {
			myProduct = product.findProductBySKU(sku);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myProduct;
	}
}
