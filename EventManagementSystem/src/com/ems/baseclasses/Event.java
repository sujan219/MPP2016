package com.ems.baseclasses;

import java.util.Calendar;
import java.util.List;

public abstract class Event extends DataObject {

	private int id;
	private String location;
	private String description;
	private Calendar startDateTime;
	private Calendar endDateTime;
	private String type;
	private double fund;
	
	private List<Personnel> managerList;
	private List<Resource> resourceList;
	private List<Student> studentList;
	private List<Expense> expenseList;
	
	public Event(int id, String location, String description, Calendar startDateTime, Calendar endDateTime, String type, double fund){
		this.id = id;
		this.location = location;
		this.description = description;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.type = type;
		this.fund = fund;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Calendar getStartDateTime() {
		return startDateTime;
	}
	
	public Calendar getEndDateTime() {
		return endDateTime;
	}
	
	public String getType() {
		return type;
	}
	
	public double getFund() {
		return fund;
	}
}
