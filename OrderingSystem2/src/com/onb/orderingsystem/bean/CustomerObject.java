package com.onb.orderingsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class CustomerObject implements Serializable{
	
	private static final long serialVersionUID = 5979899585870350593L;
	
	private int id;
	private String name;
	private BigDecimal creditLimit;
	private BigDecimal unpaidAmt;
	private BigDecimal paidAmt;
	private Collection<OrderObject> orders;
	
	public CustomerObject() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCreditLimit(){
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit){
		this.creditLimit = creditLimit;
	}
	public BigDecimal getUnpaidAmt(){
		return unpaidAmt;
	}
	public void setUnpaidAmt(BigDecimal unpaidAmt){
		this.unpaidAmt = unpaidAmt;
	}
	public BigDecimal getPaidAmt(){
		return paidAmt;
	}
	public void setPaidAmt(BigDecimal paidAmt){
		this.paidAmt = paidAmt;
	}

	public void setOrders(Collection<OrderObject> orders) {
		this.orders = orders;
	}

	public Collection<OrderObject> getOrders() {
		return orders;
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
		CustomerObject other = (CustomerObject) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

