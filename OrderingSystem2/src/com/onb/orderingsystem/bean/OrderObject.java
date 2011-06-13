package com.onb.orderingsystem.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class OrderObject implements Serializable {
	 
	private static final long serialVersionUID = -3652535993930248549L;
	
		private BigDecimal total;
	    private int id;
	    private Collection<OrderItemObject> orders;
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

		public Collection<OrderItemObject> getOrders() {
			return orders;
		}

		public void setOrders(Collection<OrderItemObject> orders) {
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
		
		public void setId(int id) {
			this.id = id;
		}
	
	@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + status;
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
			OrderObject other = (OrderObject) obj;
			if (status != other.status)
				return false;
			return true;
		}    
	    
}

