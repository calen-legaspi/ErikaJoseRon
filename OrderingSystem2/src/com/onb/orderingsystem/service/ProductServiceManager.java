package com.onb.orderingsystem.service;

import java.sql.SQLException;

import com.onb.orderingsystem.bean.ProductObject;
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
	
	public ProductObject toProductObjectBean(Product product){
		ProductObject productObject = new ProductObject();
		productObject.setName(product.getName());
		productObject.setPrice(product.getPrice());
		productObject.setSku(product.getSkuNumber());
		return productObject;
	}
	
	public ProductObject findProductBySKU(String sku){
		ProductDAO product = dao.getProductDAO();
		Product myProduct = null;
		try {
			myProduct = product.findProductBySKU(sku);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toProductObjectBean(myProduct);
	}
	
	public Product findProduct(String sku){//I need Products, not ProductBeans to instantiate OrderItem so I can create Orders! 
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
