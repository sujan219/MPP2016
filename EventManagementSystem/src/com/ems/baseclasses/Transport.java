package com.ems.baseclasses;

public class Transport extends DataObject {
	private int id;
	private String name;
	private String description;
	private int capacity;
	private Personnel driver;
	
	public Transport(int id, String name, String description, int capacity)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.capacity = capacity;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getCapacity() {
		return capacity;
	}
	
	public Personnel getDriver() {
		return driver;
	}
	
	public void setDriver(Personnel driver){
		this.driver = driver;
	}
	
	@Override
	public String toString() {
		String str = name;
		if(driver != null){
			str += " : " + driver.toString();
		}
		return str;
	}
}
