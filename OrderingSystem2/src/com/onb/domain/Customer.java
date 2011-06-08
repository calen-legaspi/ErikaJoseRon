package com.onb.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Set;


public class Customer {

	private final int id;
	private String name;
	private BigDecimal creditLimit; //calculated value
	private Set<Order> paidOrders;
	private Set<Order> unpaidOrders;
	private BigDecimal totalPaidAmt;
	private BigDecimal totalUnpaidAmt;
	
	public Customer(int id, String name){
		this.id = id;
		this.name = name;
		this.creditLimit = new BigDecimal("10000.00");	
		this.paidOrders = new LinkedHashSet<Order>();
		this.unpaidOrders = new LinkedHashSet<Order>();
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
		for(Order order: paidOrders){
			this.totalPaidAmt = this.totalPaidAmt .add(order.getTotal());
		}
		return this.totalPaidAmt.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getTotalUnpaidAmount(){
		this.totalUnpaidAmt = new BigDecimal("0.00");
		for(Order order: unpaidOrders){
			this.totalUnpaidAmt = this.totalUnpaidAmt.add(order.getTotal());
		}
		return this.totalUnpaidAmt.setScale(2, RoundingMode.HALF_UP);
	}
	
	public Set<Order> getUnpaidOrders(){
		return this.unpaidOrders;
	}
	
	public Set<Order> getPaidOrders(){
		return this.paidOrders;
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
		this.unpaidOrders.add(order);
		this.creditLimit = this.creditLimit.subtract(order.getTotal());
	}
	
	public void payOrder(Order order){
		if(order==null){
			throw new NullPointerException();
		}
		
		if(!(unpaidOrders.contains(order))){
			throw new IllegalArgumentException();
		}
		this.unpaidOrders.remove(order);
		this.paidOrders.add(order);
		
		order.setAsPaid();
		BigDecimal amount = new BigDecimal("0"); 
		amount = order.getTotal();
		addTotalPaidAmount(amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creditLimit == null) ? 0 : creditLimit.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((paidOrders == null) ? 0 : paidOrders.hashCode());
		result = prime * result
				+ ((totalPaidAmt == null) ? 0 : totalPaidAmt.hashCode());
		result = prime * result
				+ ((totalUnpaidAmt == null) ? 0 : totalUnpaidAmt.hashCode());
		result = prime * result
				+ ((unpaidOrders == null) ? 0 : unpaidOrders.hashCode());
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
		if (creditLimit == null) {
			if (other.creditLimit != null)
				return false;
		} else if (!creditLimit.equals(other.creditLimit))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (paidOrders == null) {
			if (other.paidOrders != null)
				return false;
		} else if (!paidOrders.equals(other.paidOrders))
			return false;
		if (totalPaidAmt == null) {
			if (other.totalPaidAmt != null)
				return false;
		} else if (!totalPaidAmt.equals(other.totalPaidAmt))
			return false;
		if (totalUnpaidAmt == null) {
			if (other.totalUnpaidAmt != null)
				return false;
		} else if (!totalUnpaidAmt.equals(other.totalUnpaidAmt))
			return false;
		if (unpaidOrders == null) {
			if (other.unpaidOrders != null)
				return false;
		} else if (!unpaidOrders.equals(other.unpaidOrders))
			return false;
		return true;
	}
	
}
