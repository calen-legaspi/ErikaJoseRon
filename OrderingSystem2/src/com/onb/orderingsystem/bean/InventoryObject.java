package com.onb.orderingsystem.bean;

import java.io.Serializable;
import com.onb.orderingsystem.domain.Product;

public class InventoryObject implements Serializable{
	
	private static final long serialVersionUID = -3452206286244768177L;
	public InventoryObject(){}
	
	private int id;
	private int quantity;
	private Product product;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryObject other = (InventoryObject) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	
	
	
}
