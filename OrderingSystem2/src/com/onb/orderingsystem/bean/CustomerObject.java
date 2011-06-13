package com.onb.orderingsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class CustomerObject implements Serializable{
	
	private static final long serialVersionUID = 5979899585870350593L;
	
	private int id;
	private String name;
	private BigDecimal creditLimit;
	private BigDecimal unpaidAmt;
	private BigDecimal paidAmt;
	private Set<OrderObject> orders;
	
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

	public void setOrders(Set<OrderObject> orders) {
		this.orders = orders;
	}

	public Set<OrderObject> getOrders() {
		return orders;
	}
}

