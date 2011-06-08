package com.onb.orderingsystem.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Set;


public class Customer {

	private final int id;
	private String name;
	private BigDecimal creditLimit; //calculated value
	private Set<Order> orders; //combined Set<Order> unpaidOrders and Set<Order> paidOrders;
	private BigDecimal totalPaidAmt;
	private BigDecimal totalUnpaidAmt;
	
	public Customer(int id, String name){
		this.id = id;
		this.name = name;
		this.creditLimit = new BigDecimal("10000.00");	
		this.orders = new LinkedHashSet<Order>();
		this.totalPaidAmt = new BigDecimal("0");
		this.totalUnpaidAmt = new BigDecimal("0");
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public BigDecimal getCreditLimit(){
		return this.creditLimit.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getTotalPaidAmount(){
		this.totalPaidAmt = new BigDecimal("0.00");
		for(Order order: orders){
			if(!(order.isPaid())){ //unpaid is false
				this.totalPaidAmt = this.totalPaidAmt .add(order.getTotal());
			}
		}
		return this.totalPaidAmt.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getTotalUnpaidAmount(){
		this.totalUnpaidAmt = new BigDecimal("0.00");
		for(Order order: orders){
			if(order.isPaid()){ // unpaid is true
				this.totalUnpaidAmt = this.totalUnpaidAmt.add(order.getTotal());
			}
		}
		return this.totalUnpaidAmt.setScale(2, RoundingMode.HALF_UP);
	}
	
	public Set<Order> getUnpaidOrders(){
		Set<Order> unpaidOrders = new LinkedHashSet<Order>();
		for(Order order: orders){
			if(order.isPaid()){ // unpaid is true
				unpaidOrders.add(order);
			}
		}
		return unpaidOrders;
	}
	
	public Set<Order> getPaidOrders(){
		Set<Order> paidOrders = new LinkedHashSet<Order>();
		for(Order order: orders){
			if(!(order.isPaid())){ // unpaid is false
				paidOrders.add(order);
			}
		}
		return paidOrders;
	}
	
	private void updateCreditLimit(){
		if(this.totalPaidAmt.compareTo(new BigDecimal("1000000"))==1){
			this.creditLimit = new BigDecimal("150000");
		}
		else
		if(this.totalPaidAmt.compareTo(new BigDecimal("500000"))==1){
			this.creditLimit = new BigDecimal("75000");
		}
		else
		if(this.totalPaidAmt.compareTo(new BigDecimal("100000"))==1){
			this.creditLimit = new BigDecimal("30000");
		}
		else
		this.creditLimit = new BigDecimal("30000");
	}
	
	private void addTotalPaidAmount(BigDecimal amount){
		this.totalPaidAmt = amount.add(this.totalPaidAmt);
		updateCreditLimit();
	}
	
	public void addUnpaidOrder(Order order){
		this.orders.add(order);
		this.creditLimit = this.creditLimit.subtract(order.getTotal());
	}
	
	public void payOrder(Order order){
		if(order==null){
			throw new NullPointerException();
		}
		
		if(!(orders.contains(order))){
			throw new IllegalArgumentException();
		}
		
		order.setAsPaid();
		BigDecimal amount = new BigDecimal("0"); 
		amount = order.getTotal();
		addTotalPaidAmount(amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		
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
		Customer other = (Customer) obj;
		
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		return true;
	}
	
}
