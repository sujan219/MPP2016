package com.ems.baseclasses;

public class Personnel extends DataObject{
	private int id;
	private String name;
	private String description;
	private boolean isEventManager;
	private boolean isDriver;
	
	public Personnel(int id, String name, String description, boolean isEventManager, boolean isDriver){
		this.id = id;
		this.name = name;
		this.description = description;
		this.isEventManager = isEventManager;
		this.isDriver = isDriver;
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
	
	public boolean isEventManager() {
		return isEventManager;
	}
	
	public boolean isDriver() {
		return isDriver;
	}
}