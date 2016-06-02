package com.ems.baseclasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Event extends DataObject {

	private int id;
	private String name;
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
	
	public static SimpleDateFormat dateFormat;
	static{
		dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	}
	
	public Event(int id, String name, String location, String description, Calendar startDateTime, Calendar endDateTime, String type, double fund){
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.type = type;
		this.fund = fund;
		
		managerList = new ArrayList<>();
		resourceList = new ArrayList<>();
		studentList = new ArrayList<>();
		expenseList = new ArrayList<>();
	}
	
	@Override
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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
	
	public List<Personnel> getManagerList() {
		return managerList;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}
	
	public List<Expense> getExpenseList() {
		return expenseList;
	}
	
	public void setResourceList(List<Resource> r){
		resourceList = r;
	}
	
	public void setStudentList(List<Student> s){
		studentList = s;
	}
	
	public void setEventManagerList(List<Personnel >p){
		managerList = p;
	}
	
	public void setExpenseList(List<Expense> e) {
		expenseList = e;
	}
	
	private void generateReport() {
		// TODO Auto-generated method stub
	}
}
