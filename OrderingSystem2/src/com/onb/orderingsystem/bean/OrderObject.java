package com.onb.orderingsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.onb.orderingsystem.domain.OrderItem;

public class OrderObject implements Serializable {
	 
	private static final long serialVersionUID = -3652535993930248549L;
	
		private BigDecimal total;
	    private int id;
	    private Set<OrderItem> orders;
	    private Date date;
	    private int status;
	    private int customerID;

	    public OrderObject(){ }

		public BigDecimal getTotal() {
			return total;
		}

		public void setTotal(BigDecimal total) {
			this.total = total;
		}

		public Set<OrderItem> getOrders() {
			return orders;
		}

		public void setOrders(Set<OrderItem> orders) {
			this.orders = orders;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getCustomerID() {
			return customerID;
		}

		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}

		public int getId() {
			return id;
		}
	    
	    
}
