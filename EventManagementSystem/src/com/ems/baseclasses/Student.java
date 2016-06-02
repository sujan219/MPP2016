package com.ems.baseclasses;

public class Student extends DataObject{
	private int id;
	private String name;
	private String email;
	private String entry;
	
	public Student(int id, String name, String email, String entry)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.entry = entry;
				
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getEntry() {
		return entry;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}