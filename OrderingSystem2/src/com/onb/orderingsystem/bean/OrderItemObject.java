package com.onb.orderingsystem.bean;

import java.math.BigDecimal;

public class OrderItemObject {
	private int id;
    private int quantity;
    private ProductObject product;
    private BigDecimal amount;
    
    public OrderItemObject(){ }

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

	public ProductObject getProduct() {
		return product;
	}

	public void setProduct(ProductObject product) {
		this.product = product;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
    
    
}
