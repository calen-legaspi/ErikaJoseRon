package com.onb.orderingsystem.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order {
	private BigDecimal total;
	private final int id;
	private Set<OrderItem> orders;
	private GregorianCalendar date; //Date - flexible
	private boolean unpaid;
	
	public Order(int id){
		this.id = id;
		Calendar calendar = Calendar.getInstance();
		this.date = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		this.date.clear(Calendar.MILLISECOND);
		this.orders = new LinkedHashSet<OrderItem>();
		this.unpaid = true;
	}
	
	public void setDate(GregorianCalendar date){
		this.date = date;
	}
	
	public void setTotal(BigDecimal amount){
		this.total = amount;
	}
	
	public void setAsPaid(){
		this.unpaid = false;
	}
	
	public int getId(){
		return this.id;
	}
	
	public boolean isPaid(){
		return this.unpaid;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public Set<OrderItem> getOrders(){
		return this.orders;
	}
	
	public BigDecimal getTotal(){
		this.total = new BigDecimal("0");
		for(OrderItem item: orders){
			this.total = this.total.add(item.computeAmount());
		}
		return this.total;
	}
	
	private boolean isInOrderList(OrderItem orderItem){
		for(OrderItem item: orders){
			if(item.getProduct().equals(orderItem.getProduct())){
				return true;
			}
		}
		return false;
	}

	private void updateQuantity(OrderItem orderItem){
		for(OrderItem item: orders){
			if(item.getProduct().equals(orderItem.getProduct())){
				item.addQuantity(orderItem.getQuantity());
				break;
			}
		}
	}
	
	public void add(OrderItem item){
		if(isInOrderList(item)){
			updateQuantity(item);
		}
		else
		{
			this.orders.add(item);
		}
	}
	
	public void delete(OrderItem item){
		if(isInOrderList(item))
		{
			this.orders.remove(item);
		}
		else
		{
			throw new IllegalArgumentException();
		}
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
		Order other = (Order) obj;
		
		if (id != other.id)
			return false;
		return true;
	}

}
