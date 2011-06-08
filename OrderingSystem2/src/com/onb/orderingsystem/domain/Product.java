package com.onb.orderingsystem.domain;

import java.math.BigDecimal;

public class Product {
	private String name;
	private final int skuNumber;
	private BigDecimal price;
	
	public Product(int skuNumber){
		this.skuNumber = skuNumber;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public String getName(){
		return this.name;
	}
	
	public BigDecimal getPrice(){
		return this.price;
	}
	
	public int getSkuNumber(){
		return this.skuNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + skuNumber;
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
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (skuNumber != other.skuNumber)
			return false;
		return true;
	}


}
