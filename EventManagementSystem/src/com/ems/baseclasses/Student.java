package com.ems.baseclasses;

public class Student {
	private int id;
	private String name;
	private String email;
	private String entry;
	public int getId() {
		return id;
	}
	public Student(int id, String name, String email, String entry)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.entry = entry;
				
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
}
