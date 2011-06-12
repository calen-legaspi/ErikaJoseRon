package com.onb.orderingsystem.bean;

import java.io.Serializable;

public class CustomerObject implements Serializable{
	
	private static final long serialVersionUID = 5979899585870350593L;
	
	private int id;
	private String name;
	
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
	
}
