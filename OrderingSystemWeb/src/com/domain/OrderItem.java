package com.domain;

import java.math.BigDecimal;

import com.domain.Product;


public class OrderItem {
	private int quantity;
	private Product product;
	private BigDecimal amount;
	
	public OrderItem(Product product, int quantity){
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct(){
		return this.product;
	}

	public int getQuantity(){
		return this.quantity;
	}
	
	public void addQuantity(int quantity){
		this.quantity += quantity;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public BigDecimal computeAmount(){
		this.amount = product.getPrice().multiply(new BigDecimal(quantity));
		return this.amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
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
		OrderItem other = (OrderItem) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	
}
