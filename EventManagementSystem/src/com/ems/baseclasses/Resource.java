package com.ems.baseclasses;

public class Resource extends DataObject{
	private int id;
	private String name;
	private int quantity;
	private String description;
	
	public Resource(int id, String name, int quantity, String description){
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return name + " : " + quantity;
	}
}
